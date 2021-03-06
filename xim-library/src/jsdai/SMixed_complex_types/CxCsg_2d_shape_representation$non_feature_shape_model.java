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

package jsdai.SMixed_complex_types;

/**
* @author Giedrius Liutkus
* @version $$
* $$
*/

import jsdai.lang.*;
import jsdai.libutil.*;
import jsdai.SConstructive_solid_geometry_2d_mim.CCsg_2d_shape_representation;
import jsdai.SNon_feature_shape_element_xim.*;
import jsdai.SRepresentation_schema.ERepresentation;

public class CxCsg_2d_shape_representation$non_feature_shape_model extends CCsg_2d_shape_representation$non_feature_shape_model implements EMappedXIMEntity
{

	/// methods for attribute: name, base type: STRING
/*	public boolean testName(ERepresentation type) throws SdaiException {
		return test_string(a2);
	}
	public String getName(ERepresentation type) throws SdaiException {
		return get_string(a2);
	}*/
	public void setName(ERepresentation type, String value) throws SdaiException {
		a2 = set_string(value);
	}
	public void unsetName(ERepresentation type) throws SdaiException {
		a2 = unset_string();
	}
	public static jsdai.dictionary.EAttribute attributeName(ERepresentation type) throws SdaiException {
		return a2$;
	}

	public int attributeState = ATTRIBUTES_MODIFIED;	

	public void createAimData(SdaiContext context) throws SdaiException {
		if (attributeState == ATTRIBUTES_MODIFIED) {
			attributeState = ATTRIBUTES_UNMODIFIED;
		} else {
			return;
		}

		setTemp("AIM", CCsg_2d_shape_representation.definition);

		setMappingConstraints(context, this);

		// associated_element
		setAssociated_element(context, this);
		
      // model_shape 		
		setModel_shape(context, this);
		
		// clean ARM
		// associated_element
		unsetAssociated_element(null);
		
      // model_shape 		
		unsetModel_shape(null);
		
	}

	public void removeAimData(SdaiContext context) throws SdaiException {
		unsetMappingConstraints(context, this);

		// associated_element
		unsetAssociated_element(context, this);
		
      // model_shape 		
		unsetModel_shape(context, this);
	}
	
	
	/**
	* Sets/creates data for mapping constraints.
	*
	* <p>
	* mapping_constraints;
	* 	{shape_representation &lt;=
	*  representation				
	*  [representation.id = 'ac2ds']
	*  [representation.name = 'planar projected shape']
	*  [representation &lt;-
	*  property_definition_representation.used_representation
	*  property_definition_representation
	*  property_definition_representation.definition -&gt;
	*  {property_definition =&gt;
	*  product_definition_shape}
	*  property_definition
	*  property_definition.definition -&gt;
	*  characterized_definition
	*  characterized_definition = characterized_product_definition
	*  characterized_product_definition
	*  characterized_product_definition = product_definition
	*  product_definition =&gt;
	*  component_definition =&gt;
	*  assembly_component]}	
	* end_mapping_constraints;
	* </p>
	* @param context SdaiContext.
	* @param armEntity arm entity.
	* @throws SdaiException
	*/
	public static void setMappingConstraints(SdaiContext context, CCsg_2d_shape_representation$non_feature_shape_model armEntity) throws SdaiException
	{
		unsetMappingConstraints(context, armEntity);
		CxNon_feature_shape_model.setMappingConstraints(context, armEntity);
	}

	public static void unsetMappingConstraints(SdaiContext context, CCsg_2d_shape_representation$non_feature_shape_model armEntity) throws SdaiException
	{
		CxNon_feature_shape_model.unsetMappingConstraints(context, armEntity);
	}

	// Non_feature_shape_definition
	// associated_element
	public static void setAssociated_element(SdaiContext context, ENon_feature_shape_model armEntity) throws SdaiException
	{
		CxNon_feature_shape_model.setAssociated_element(context, armEntity);
	}

	public static void unsetAssociated_element(SdaiContext context, ENon_feature_shape_model armEntity) throws SdaiException
	{
		CxNon_feature_shape_model.unsetAssociated_element(context, armEntity);
	}
	
	
   // model_shape
	public static void setModel_shape(SdaiContext context, ENon_feature_shape_model armEntity) throws SdaiException
	{
		CxNon_feature_shape_model.setModel_shape(context, armEntity);
	}
	
	public static void unsetModel_shape(SdaiContext context, ENon_feature_shape_model armEntity) throws SdaiException
	{
		CxNon_feature_shape_model.unsetModel_shape(context, armEntity);
	}
	
}
