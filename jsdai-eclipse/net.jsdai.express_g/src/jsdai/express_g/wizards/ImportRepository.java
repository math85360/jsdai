/*
 * $Id$
 *
 * JSDAI(TM), a way to implement STEP, ISO 10303
 * Copyright (C) 1997-2008, LKSoftWare GmbH, Germany
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License
 * version 3 as published by the Free Software Foundation (AGPL v3).
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * JSDAI is a registered trademark of LKSoftWare GmbH, Germany
 * This software is also available under commercial licenses.
 * See also http://www.jsdai.net/
 */

package jsdai.express_g.wizards;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import jsdai.express_g.SdaieditPlugin;
import jsdai.express_g.editors.RepositoryHandler;
import jsdai.express_g.editors.SdaiEditor;
import jsdai.express_g.util.repocopy.RepoCopy;
import jsdai.lang.SdaiException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.help.WorkbenchHelp;
import org.eclipse.ui.part.FileEditorInput;

/**
 * @author Mantas Balnys
 *
 */
public class ImportRepository extends Wizard implements IImportWizard {
	private SdaiEditor editor = null;
	private IWorkbenchPage workbenchPage = null;
	private Object selected = null;
	
	private RepoCopyFileSelectionPage page = null;

	/**
	 * 
	 */
	public ImportRepository() {
		super();
		IDialogSettings workbenchSettings = SdaieditPlugin.getDefault().getDialogSettings();
		IDialogSettings section = workbenchSettings.getSection("RepositoryFileWizard");//$NON-NLS-1$
		if(section == null)
			section = workbenchSettings.addNewSection("RepositoryFileWizard");//$NON-NLS-1$
		setDialogSettings(section);
	}

	/* (non-Javadoc)
	 * Method declared on IWizard.
	 */
	public void addPages() {
		super.addPages();

		if (editor != null) {
			page = new RepoCopyFileSelectionPage(editor.getRepositoryHandler().getRepoPath().removeLastSegments(1),
					"Select Express repository file", "File:", "Select SDAI File", RepoCopyFileSelectionPage.DIALOG_FILE, 
					SWT.OPEN | SWT.SINGLE, new String[]{"*.exd", "*.exg", "*.sdai"}, false);
			page.setTitle("Select JSDAI Express dictionary (*.exd) file");
//				page.setDescription("Select JSDAI Express dictionary (*.exd) file");
			addPage(page);
		} else {
			WizardInfoPage wp = new WizardInfoPage("Error", "");//"This wizard can be started only on opened Express-G file (*.exg)");
			wp.setTitle("Import Express Data");
			wp.setDescription("This wizard can be started only on opened Express-G file (*.exg)");
			addPage(wp);
		}
	}
	
	/* (non-Javadoc)
	 * Method declared on IWizard.
	 */
	public boolean performFinish() {
		boolean ok = true;
		MessageDialog outOfMemDialog = new MessageDialog(getShell(), "OutOfMemoryError", null, 
				"Out of memory. Increase memory size for eclipse (example: eclipse -vmargs -Xmx512M)\nTerminate current eclipse instance (recomended) or try to continue with unstable system.", 
				MessageDialog.ERROR, new String[]{"Terminate", "Continue"}, 0);
		workbenchPage.saveEditor(editor, true);
		if (!editor.isDirty()) {
			RepositoryHandler handler = editor.getRepositoryHandler();
			try {
				Runnable runnable = new Runnable(handler, page.getFileName());
				getContainer().run(true, true, runnable); // TODO calcelable
				ok = runnable.niceDone();
				if (ok) {
					editor.closeInternalEditors();
				}
/*
				IFile file = ((SdaiEditorInput)editor.getEditorInput()).getFile();
				if (workbenchPage.closeEditor(editor, false)) { // interesting but not suitable (at least now)
					SdaiEditorInput input = new SdaiEditorInput(file, false);
					try {
						workbenchPage.openEditor(input, SdaiEditor.ID, true);
					} catch (PartInitException pie) {
						SdaieditPlugin.log(pie);
					}
				}*/
			} catch (InvocationTargetException e) {
				if (e.getCause() instanceof OutOfMemoryError) {
					if (outOfMemDialog.open() == 0)
						SdaieditPlugin.getDefault().getWorkbench().close();
				}
				SdaieditPlugin.log(e);
				ok = false;
				SdaieditPlugin.console(e.toString());
			} catch (InterruptedException e) {
				if (e.getCause() instanceof OutOfMemoryError) {
					if (outOfMemDialog.open() == 0)
						SdaieditPlugin.getDefault().getWorkbench().close();
				}
				SdaieditPlugin.log(e);
				ok = false;
				SdaieditPlugin.console(e.toString());
			}
			editor.refreshOutline();
			try {
				IPath path = new Path(page.getFileName());
				IResource file = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(path);
				file.refreshLocal(IResource.DEPTH_INFINITE, null);
				path = handler.getRepoPath();
				file = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(path);
				file.refreshLocal(IResource.DEPTH_INFINITE, null);
			} catch (Throwable t) {
				t.printStackTrace();
			}
		} else {
			MessageDialog.openWarning(editor.getSite().getShell(), "JSDAI Express Dictionary data Import", "Operation aborted by user");
		}
		return ok;
	}
	
	private static class Runnable implements IRunnableWithProgress {
		private RepositoryHandler handler;
		private String file;
		private boolean ok = true;
		
		/**
		 * @param handler
		 * @param file
		 */
		public Runnable(RepositoryHandler handler, String file) {
			this.handler = handler;
			this.file = file;
		}

		public void run(IProgressMonitor progress) {
			progress.beginTask("Importing External repository", IProgressMonitor.UNKNOWN);
			if (file != null) {
				try {
					RepoCopy.synchronizedRepoCopy(handler, file, progress);
				} catch (SdaiException e) {
					SdaieditPlugin.log(e);
					ok = false;
					SdaieditPlugin.console(e.toString());
				}
			} else {
				ok = false;
			}
			progress.done();
		}
		
		public boolean niceDone() {
			return ok;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection select) {
		selected = select.getFirstElement();
		workbenchPage = workbench.getActiveWorkbenchWindow().getActivePage();
		IEditorPart part = workbenchPage.getActiveEditor();
		if (part instanceof SdaiEditor) {
			editor = (SdaiEditor)part;

		}
		setWindowTitle("Importing JSDAI Express dictionary data"); //$NON-NLS-1$
		setNeedsProgressMonitor(true);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.IWizard#canFinish()
	 */
	public boolean canFinish() {
		return (editor != null)&&(page != null)&&(page.determinePageCompletion());
	}
	
	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);
		
		// TODO enable selection (needs implementation in RepoCopy class)
		if (page != null) {
			page.setEnabledDict(false);
			page.setEnabledEG(false);

			WorkbenchHelp.setHelp(page.getControl(), SdaieditPlugin.ID_SDAIEDIT + ".ImportRepositoryContextId");


// old implementation - initializes to selected exg, exd or sdai file or, if different - to the last previously selected exg/exd/sdai 
/*	
			if (selected instanceof IFile) {
				IFile file = (IFile)selected;
				String fext = file.getFileExtension();
				if (fext.equalsIgnoreCase("exd") || fext.equalsIgnoreCase("exg") || 
						fext.equalsIgnoreCase("sdai")) {
					page.setSourceName(file.getLocation().toOSString());
				}
				

			}
*/
			// new implementation - always initializes to exd file with the name = project name
			// still, perhaps no need to require selection - just get the project from the opened exg file in express-g editor
/*
			String initial_path = "failed to calculate exd file";
			if (selected instanceof IResource) {
				IProject fProject = ((IResource)selected).getProject();
				if (fProject != null) {
//					String initial_path = file.getLocation().toOSString();
					// initial_path = file.getLocation().toOSString();
					initial_path = fProject.getLocation().toOSString() + File.separator  + fProject.getName() + ".exd";
				} else {
				}
			} else { 
			}
			page.setSourceName(initial_path);
*/

			// even newer implementation - this one does not depend on something selected so Import can be invoked from the main menu, etc.
			// System.out.println("editor: " + editor + ", input: " + editor.getEditorInput());
			IFile input_file = ((FileEditorInput)editor.getEditorInput()).getFile();
			IProject fProject = input_file.getProject();
			String initial_path = fProject.getLocation().toOSString();

			if (initial_path.endsWith("\\") || initial_path.endsWith("/")) {
				initial_path += fProject.getName() + ".exd";
			} else {
				initial_path +=  File.separator + fProject.getName() + ".exd";
			}

			page.setSourceName(initial_path);
		
		}
	}
}

