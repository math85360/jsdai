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

package jsdai.express_compiler.editor;

import jsdai.express_compiler.preferences.ExpressEditorPreferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.internal.editors.text.EditorsPlugin;

/*
   Express comment scanner
 	 
 	 had to implement this scanner just so that multi-line comments are refreshed at once when their color, etc. is changed.
 	 I was not able to find a way to do it without a separate scanner.
 	 When we have the scanner, we can put new rules into it every time the color or font style changes.
 
 */
public class ExpressSingleLineCommentScanner extends RuleBasedScanner implements IExpressKeywords {

//	private TextAttribute fComment;
//	private TextAttribute fKeyword;
//	private TextAttribute fType;
//	private TextAttribute fString;
//  private TextAttribute fOperator;
//  private TextAttribute fBuiltin;
//	private ExpressColorProvider fColorProvider;



	/**
	 * Creates Express code scanner with the given color provider.
	 * 
	 * @param provider the color provider
	 */
	public ExpressSingleLineCommentScanner(ExpressColorProvider provider) {	


/*
//		IToken keyword = new Token(new TextAttribute(provider.getColor(ExpressColorProvider.KEYWORD)));
		// let's have more bang
		IToken keyword = new Token(new TextAttribute(provider.getColor(ExpressColorProvider.KEYWORD), provider.getColor(ExpressColorProvider.BACKGROUND), SWT.BOLD));
//		IToken type = new Token(new TextAttribute(provider.getColor(ExpressColorProvider.TYPE)));
		IToken type = new Token(new TextAttribute(provider.getColor(ExpressColorProvider.TYPE), provider.getColor(ExpressColorProvider.BACKGROUND), SWT.BOLD));
//		IToken operator = new Token(new TextAttribute(provider.getColor(ExpressColorProvider.OPERATOR)));
		IToken operator = new Token(new TextAttribute(provider.getColor(ExpressColorProvider.OPERATOR), provider.getColor(ExpressColorProvider.BACKGROUND), SWT.BOLD));
//		IToken builtin = new Token(new TextAttribute(provider.getColor(ExpressColorProvider.BUILTIN)));
		IToken builtin = new Token(new TextAttribute(provider.getColor(ExpressColorProvider.BUILTIN), provider.getColor(ExpressColorProvider.BACKGROUND), SWT.BOLD));
		IToken string = new Token(new TextAttribute(provider.getColor(ExpressColorProvider.STRING)));
		IToken comment = new Token(new TextAttribute(provider.getColor(ExpressColorProvider.SINGLE_LINE_COMMENT)));
		IToken other = new Token(new TextAttribute(provider.getColor(ExpressColorProvider.DEFAULT)));
*/



		// let's have more bang

			initRules(provider);

		}
		
		public void initRules(ExpressColorProvider provider) {
		
		
		

		IPreferenceStore store = EditorsPlugin.getDefault().getPreferenceStore();


		boolean slc_bold = store.getBoolean(ExpressEditorPreferences.EXPRESS_SINGLE_BOLD);
		boolean slc_italic = store.getBoolean(ExpressEditorPreferences.EXPRESS_SINGLE_ITALIC); 
		boolean slc_strike = store.getBoolean(ExpressEditorPreferences.EXPRESS_SINGLE_STRIKE);
		boolean slc_underline = store.getBoolean(ExpressEditorPreferences.EXPRESS_SINGLE_UNDERLINE);
		int slc_flags = 0; 
		if (slc_bold) slc_flags |= SWT.BOLD;
		if (slc_italic) slc_flags |= SWT.ITALIC;
		if (slc_strike) slc_flags |= TextAttribute.STRIKETHROUGH;
		if (slc_underline) slc_flags |= TextAttribute.UNDERLINE;

		Color slc_bg = null;
		boolean slc_transparent = store.getBoolean(ExpressEditorPreferences.EXPRESS_SINGLE_TRANSPARENT);
		if (!slc_transparent) {
			slc_bg =provider.getColor(provider.getColorPreference(ExpressEditorPreferences.EXPRESS_SINGLE_BG_COLOR));
		}

//		ExpressColorProvider provider = ExpressCompilerPlugin.getDefault().getExpressColorProvider();

	
		IToken singleComment = new Token(new TextAttribute(provider.getColor(provider.getColorPreference(ExpressEditorPreferences.EXPRESS_SINGLE_COLOR)), slc_bg, slc_flags));


//		IToken multiLineComment= new Token(ExpressConstants.EXPRESS_MULTILINE_COMMENT);

		
		
		







		setDefaultReturnToken(singleComment);
		
		
/*		
		
		List rules = new ArrayList();

		// Add rule for single line comments.
		rules.add(new EndOfLineRule("--", comment)); //$NON-NLS-1$

		// Add rule for strings and character constants.
		rules.add(new SingleLineRule("'", "'", string, '\\')); //$NON-NLS-2$ //$NON-NLS-1$
		rules.add(new SingleLineRule("\"", "\"", string, '\\')); //$NON-NLS-2$ //$NON-NLS-1$

		// Add generic whitespace rule.
		rules.add(new WhitespaceRule(new ExpressWhiteSpaceDetector()));


		// Add rule for express operators
		rules.add(new OperatorRule(operator));



		// Add word rule for keywords, types, operators, built-in constants, functions, procedures
//		WordRule wordRule= new WordRule(new ExpressWordDetector(), other);
		ExpressWordRule wordRule= new ExpressWordRule(new ExpressWordDetector(), other);

		for (int i= 0; i < keywords.length; i++)
			wordRule.addWord(keywords[i], keyword);

		for (int i= 0; i < types.length; i++)
			wordRule.addWord(types[i], type);

		for (int i= 0; i < operators.length; i++)
			wordRule.addWord(operators[i], operator);

		for (int i= 0; i < builtin_constants.length; i++)
			wordRule.addWord(builtin_constants[i], builtin_constant);

		for (int i= 0; i < builtin_functions.length; i++)
			wordRule.addWord(builtin_functions[i], builtin_function);

		for (int i= 0; i < builtin_procedures.length; i++)
			wordRule.addWord(builtin_procedures[i], builtin_procedure);

		rules.add(wordRule);

		IRule[] result= new IRule[rules.size()];
		rules.toArray(result);
		setRules(result);

*/


	}	

}

