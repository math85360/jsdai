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
import jsdai.SLayered_interconnect_complex_template_xim.CxMulti_stratum_structured_template_armx;
import jsdai.SLayered_interconnect_complex_template_xim.CxStratum_stack_dependent_template_armx;
import jsdai.SLayered_interconnect_complex_template_xim.EMulti_stratum_structured_template_armx;
import jsdai.SLayered_interconnect_complex_template_xim.EStratum_stack_dependent_template_armx;
import jsdai.SPart_template_xim.*;
import jsdai.SPrinted_physical_layout_template_xim.CxMulti_stratum_printed_part_template_armx;
import jsdai.SPrinted_physical_layout_template_xim.CxPrinted_part_template_armx;
import jsdai.SPrinted_physical_layout_template_xim.EStructured_printed_part_template_armx;
import jsdai.SProduct_definition_schema.*;
import jsdai.SProduct_property_definition_schema.*;
import jsdai.SProduct_view_definition_xim.*;

public class CxMulti_stratum_printed_part_template_armx$stratum_stack_dependent_template_armx extends CMulti_stratum_printed_part_template_armx$stratum_stack_dependent_template_armx implements EMappedXIMEntity
{

	public int attributeState = ATTRIBUTES_MODIFIED;	

	// Taken from Physical_unit - Property_definition
	/// methods for attribute: name, base type: STRING
/*	public boolean testName(jsdai.SProduct_property_definition_schema.EProperty_definition type) throws SdaiException {
		return test_string(a8);
	}
	public String getName(jsdai.SProduct_property_definition_schema.EProperty_definition type) throws SdaiException {
		return get_string(a8);
	}*/
	public void setName(jsdai.SProduct_property_definition_schema.EProperty_definition type, String value) throws SdaiException {
		a8 = set_string(value);
	}
	public void unsetName(jsdai.SProduct_property_definition_schema.EProperty_definition type) throws SdaiException {
		a8 = unset_string();
	}
	public static jsdai.dictionary.EAttribute attributeName(jsdai.SProduct_property_definition_schema.EProperty_definition type) throws SdaiException {
		return a8$;
	}
	// ENDOF Taken from Physical_unit - Property_definition
	
	// Product_view_definition
	// From property_definition
/*	public static int usedinDefinition(EProperty_definition type, EEntity instance, ASdaiModel domain, AEntity result) throws SdaiException {
		return ((CEntity)instance).makeUsedin(definition, a10$, domain, result);
	}
	public boolean testDefinition(EProperty_definition type) throws SdaiException {
		return test_instance(a10);
	}

	public EEntity getDefinition(EProperty_definition type) throws SdaiException { // case 1
		a10 = get_instance_select(a10);
		return (EEntity)a10;
	}
*/
	public void setDefinition(EProperty_definition type, EEntity value) throws SdaiException { // case 1
		a10 = set_instanceX(a10, value);
	}

	public void unsetDefinition(EProperty_definition type) throws SdaiException {
		a10 = unset_instance(a10);
	}

	public static jsdai.dictionary.EAttribute attributeDefinition(EProperty_definition type) throws SdaiException {
		return a10$;
	}
	
	// END OF Property_definition

	/// methods for attribute: description, base type: STRING
/*	public boolean testDescription(EProduct_definition type) throws SdaiException {
		return test_string(a3);
	}
	public String getDescription(EProduct_definition type) throws SdaiException {
		return get_string(a3);
	}*/
	public void setDescription(EProduct_definition type, String value) throws SdaiException {
		a3 = set_string(value);
	}
	public void unsetDescription(EProduct_definition type) throws SdaiException {
		a3 = unset_string();
	}
	public static jsdai.dictionary.EAttribute attributeDescription(EProduct_definition type) throws SdaiException {
		return a3$;
	}

	// methods for derived attribute: name, base type: STRING
/*	public boolean testName(EProduct_definition type) throws SdaiException {
			throw new SdaiException(SdaiException.FN_NAVL);
	}
	public Value getName(EProduct_definition type, SdaiContext _context) throws SdaiException {
			throw new SdaiException(SdaiException.FN_NAVL);
	}
	public String getName(EProduct_definition type) throws SdaiException {
			throw new SdaiException(SdaiException.FN_NAVL);
	}*/
	public static jsdai.dictionary.EAttribute attributeName(EProduct_definition type) throws SdaiException {
		return d0$;
	}
	
	public void createAimData(SdaiContext context) throws SdaiException {
		if (attributeState == ATTRIBUTES_MODIFIED) {
			attributeState = ATTRIBUTES_UNMODIFIED;
		} else {
			return;
		}

		setTemp("AIM", CMulti_stratum_printed_part_template$stratum_stack_dependent_template.definition);

		setMappingConstraints(context, this);

		// SETTING DERIVED
		// setDefinition(null, this);

		
		setPhysical_characteristic(context, this);
		//********** "managed_design_object" attributes

		//********** "item_shape" attributes
//		setId_x(context, this);

		// Clean ARM specific attributes
		
		//	********** "product_view_definition" attributes
		//id - goes directly into AIM
		
		//additional_characterization
		setAdditional_characterization(context, this);

		//additional_context
		setAdditional_contexts(context, this);
		
		setLocation(context, this);
		// setReference_breakout(context, this);
		
		setLocation(context, this);

		setImplemented_function(context, this);
		
		setStack(context, this);
		
		// Clean ARM specific attributes
//		unsetId_x(null);
		unsetAdditional_characterization(null);
		unsetAdditional_contexts(null);
		unsetPhysical_characteristic(null);
		unsetLocation(null);
		unsetImplemented_function(null);
		unsetStack(null);
	}

	public void removeAimData(SdaiContext context) throws SdaiException {

		unsetMappingConstraints(context, this);
		
		// unsetDefinition(null);

		unsetPhysical_characteristic(context, this);
		//********** "managed_design_object" attributes

		//********** "item_shape" attributes
//		unsetId_x(context, this);

		//	********** "product_view_definition" attributes
		//id - goes directly into AIM
		
		//additional_characterization
		unsetAdditional_characterization(context, this);

		//additional_context
		unsetAdditional_contexts(context, this);
		
		unsetImplemented_function(context, this);

		unsetLocation(context, this);
		
		unsetStack(context, this);
//		 It is derived, so can't unset it unsetLocation(context, this);

		// unsetReference_breakout(context, this);
		
	}
	
	
	/**
	* Sets/creates data for mapping constraints.
	*
	* <p>
	* mapping_constraints;
	* 	footprint_definition &lt;= 
	* 	part_template_definition &lt;=
	* 	product_definition
	* 	{[product_definition
	* 	product_definition.formation -&gt;
	* 	product_definition_formation
	* 	product_definition_formation.of_product -&gt;
	* 	product &lt;-
	* 	product_related_product_category.products[i]
	* 	product_related_product_category &lt;=
	* 	product_category
	* 	product_category.name = 'template model'] (* Comes from supertype *)
	* 	[product_definition
	* 	product_definition.frame_of_reference -&gt;
	* 	product_definition_context &lt;=
	* 	application_context_element
	* 	application_context_element.name = 'layout design usage']} issue 5 in stepmod	
	* end_mapping_constraints;
	* </p>
	* @param context SdaiContext.
	* @param armEntity arm entity.
	* @throws SdaiException
	*/
	public static void setMappingConstraints(SdaiContext context, CMulti_stratum_printed_part_template_armx$stratum_stack_dependent_template_armx armEntity) throws SdaiException
	{
		unsetMappingConstraints(context, armEntity);
		
		CxMulti_stratum_printed_part_template_armx.setMappingConstraints(context, armEntity);
		CxStratum_stack_dependent_template_armx.setMappingConstraints(context, armEntity);
	}

	public static void unsetMappingConstraints(SdaiContext context, CMulti_stratum_printed_part_template_armx$stratum_stack_dependent_template_armx armEntity) throws SdaiException
	{
		CxMulti_stratum_printed_part_template_armx.unsetMappingConstraints(context, armEntity);
		CxStratum_stack_dependent_template_armx.unsetMappingConstraints(context, armEntity);
	}	
	//********** "managed_design_object" attributes

	//********** "item_shape" attributes
    /**
     * Sets/creates data for Id_x attribute.
     *
     * @param context SdaiContext.
     * @param armEntity arm entity.
     * @throws SdaiException
     */
	/* Removed from XIM - see bug #3610
    public static void setId_x(SdaiContext context, EItem_shape armEntity) throws SdaiException {
       CxItem_shape.setId_x(context, armEntity);
    }
*/
  /**
   * Unsets/deletes data for Id_x attribute.
   *
   * @param context SdaiContext.
   * @param armEntity arm entity.
   * @throws SdaiException
   */
	/* Removed from XIM - see bug #3610
    public static void unsetId_x(SdaiContext context, EItem_shape armEntity) throws SdaiException {
      CxItem_shape.unsetId_x(context, armEntity);
   }
*/
 	//********** "product_view_definition" attributes
    /**
     * Sets/creates data for name_x attribute.
     *
     * @param context SdaiContext.
     * @param armEntity arm entity.
     * @throws SdaiException
     */
    public static void setAdditional_characterization(SdaiContext context, EProduct_view_definition armEntity) throws SdaiException {
       CxProduct_view_definition.setAdditional_characterization(context, armEntity);
    }

  /**
   * Unsets/deletes data for Id_x attribute.
   *
   * @param context SdaiContext.
   * @param armEntity arm entity.
   * @throws SdaiException
   */
    public static void unsetAdditional_characterization(SdaiContext context, EProduct_view_definition armEntity) throws SdaiException {
      CxProduct_view_definition.unsetAdditional_characterization(context, armEntity);
   }

    /**
     * Sets/creates data for name_x attribute.
     *
     * @param context SdaiContext.
     * @param armEntity arm entity.
     * @throws SdaiException
     */
    public static void setAdditional_contexts(SdaiContext context, EProduct_view_definition armEntity) throws SdaiException {
       CxProduct_view_definition.setAdditional_contexts(context, armEntity);
    }

  /**
   * Unsets/deletes data for Id_x attribute.
   *
   * @param context SdaiContext.
   * @param armEntity arm entity.
   * @throws SdaiException
   */
    public static void unsetAdditional_contexts(SdaiContext context, EProduct_view_definition armEntity) throws SdaiException {
      CxProduct_view_definition.unsetAdditional_contexts(context, armEntity);
   }

    // Template_definition, originally developed in Part_template_definition
 	/**
 	* Sets/creates data for physical_characteristic attribute.
 	*
	*
 	* @param context SdaiContext.
 	* @param armEntity arm entity.
 	* @throws SdaiException
 	*/

 	public static void setPhysical_characteristic(SdaiContext context, ETemplate_definition armEntity) throws SdaiException
 	{
 		CxTemplate_definition.setPhysical_characteristic(context, armEntity); 		
 	}


 	/**
 	* Unsets/deletes data for physical_characteristic attribute.
 	*
 	* @param context SdaiContext.
 	* @param armEntity arm entity.
 	* @throws SdaiException
 	*/
 	public static void unsetPhysical_characteristic(SdaiContext context, ETemplate_definition armEntity) throws SdaiException
   {
 		CxTemplate_definition.unsetPhysical_characteristic(context, armEntity); 		
 	}

 	/**
 	* Sets/creates data for location attribute.
 	*
	*
 	* @param context SdaiContext.
 	* @param armEntity arm entity.
 	* @throws SdaiException
 	*/

 	public static void setLocation(SdaiContext context, EMulti_stratum_structured_template_armx armEntity) throws SdaiException
 	{
 		CxMulti_stratum_structured_template_armx.setLocation(context, armEntity);
 	}


 	/**
 	* Unsets/deletes data for physical_characteristic attribute.
 	*
 	* @param context SdaiContext.
 	* @param armEntity arm entity.
 	* @throws SdaiException
 	*/
 	public static void unsetLocation(SdaiContext context, EMulti_stratum_structured_template_armx armEntity) throws SdaiException
   {
 		CxMulti_stratum_structured_template_armx.unsetLocation(context, armEntity);
 	}
 	
	// New attributes since WD18
	/**
	 * Sets/creates data for reference_breakout.
	 * <p>
	 * attribute_mapping reference_breakout_assembly_group_component (reference_breakou
	 * , (*PATH*), assembly_group_component);
	 * (footprint_definition <=
	 * product_definition <-
	 * product_definition_relationship.related_product_definition
	 * {product_definition_relationship
	 * product_definition_relationship.name = `reference breakout'}
	 * product_definition_relationship
	 * product_definition_relationship.relating_product_definition ->
	 * product_definition =>
	 * assembly_group_component_definition)
	 * (footprint_definition <=
	 * product_definition = characterized_product_definition
	 * characterized_product_definition = characterized_definition
	 * characterized_definition <-
	 * property_definition.definition
	 * property_definition <-
	 * property_definition_relationship.related_property_definition
	 * {property_definition_relationship
	 * property_definition_relationship.name = `reference breakout'}
	 * property_definition_relationship
	 * property_definition_relationship.relating_property_definition ->
	 * property_definition
	 * property_definition.definition ->
	 * shape_aspect =>
	 * component_shape_aspect =>
	 * assembly_group_component_shape_aspect)
	 * end_attribute_mapping;
	 * </p>
	 * @param context SdaiContext.
	 * @param armEntity arm entity.
	 * @throws SdaiException
	 */
	// 1) FD <- PDR -> AGCD or 2) FD <- PropD <- PropDR -> PropD -> AGCSA
	// Implement only 1st for now
 	// MOVED to more specific subtype
 	/*
	public static void setReference_breakout(
		SdaiContext context,
		EFootprint_definition_armx armEntity)
		throws SdaiException {
		//unset old values
		unsetReference_breakout(context, armEntity);

		if (armEntity.testReference_breakout(null)) {
				AAssembly_group_component aArmAssembly_group_components =
				armEntity.getReference_breakout(null);
			for (int z = 1; z <= aArmAssembly_group_components.getMemberCount(); z++) {
				EAssembly_group_component aArmAssembly_group_component =
					aArmAssembly_group_components.getByIndex(z);
				// 1) strategy FD <- PDR -> AGCD - this is used as base strategy after modularization
					EProduct_definition_relationship epdr = (EProduct_definition_relationship) context
								.working_model.createEntityInstance(CProduct_definition_relationship.definition);
					epdr.setDescription(null, "");
					epdr.setName(null, "reference breakout");
					// PDR -> PD
					epdr.setRelating_product_definition(null, aArmAssembly_group_component);
					// FD <- PDR
					epdr.setRelated_product_definition(null, armEntity);
				// 2) FD <- PropD <- PropDR -> PropD -> AGCSA -- this is no longer used
			}
		}
	}

	public static void unsetReference_breakout(
		SdaiContext context,
		EFootprint_definition_armx armEntity)
		throws SdaiException {
		// 1) strategy FD <- PDR -> AGCD
		AProduct_definition_relationship apdr =
			new AProduct_definition_relationship();
		CProduct_definition_relationship.usedinRelated_product_definition(null, armEntity, context.domain, apdr);
		for (int i = 1; i <= apdr.getMemberCount();) {
			EProduct_definition_relationship epdr = apdr.getByIndex(i);
			if ((epdr.testName(null)) && (epdr.getName(null).equals("reference breakout"))) {
				apdr.removeByIndex(i);
				epdr.deleteApplicationInstance();
			} else {
				i++;
			}
		}
		// 2) FD <- PropD <- PropDR -> PropD -> AGCSA
		AProperty_definition apd = new AProperty_definition();
		CProperty_definition.usedinDefinition(null, armEntity, context.domain, apd);
		for (int i = 1; i <= apd.getMemberCount(); i++) {
			EProperty_definition epd = apd.getByIndex(i);
			AProperty_definition_relationship apdr2 =
				new AProperty_definition_relationship();
			CProperty_definition_relationship
				.usedinRelated_property_definition(
				null,
				epd,
				context.domain,
				apdr2);
			for (int j = 1; j <= apdr2.getMemberCount();) {
				EProperty_definition_relationship epdr = apdr2.getByIndex(j);
				if ((epdr.testName(null))
					&& (epdr.getName(null).equals("reference breakout"))) {
					apdr2.removeByIndex(j);
					epdr.deleteApplicationInstance();
				} else {
					j++;
				}
			}
		}

	}
*/	


 // New attributes since WD38
	// We need to set DERIVED attribute here:
	// footprint_location: padstack_arrangement := padstack [ 1 ] . padstack . padstack_location ;
/*
 * [product_definition 
	characterized_product_definition = product_definition 
	characterized_product_definition 
	characterized_definition = characterized_product_definition 
	characterized_definition <- 
	property_definition.definition 
	property_definition 
	{property_definition 
	property_definition.name = `product definition context description'} 
	property_definition <- 
	property_definition_representation.definition 
	property_definition_representation 
	property_definition_representation.used_representation -> 
	representation 
	{representation 
	representation.name = `product definition context description'} 
	representation.items[i] -> 
	representation_item => 
	{representation_item 
	representation_item.name = `text'} 
	descriptive_representation_item 
	{descriptive_representation_item 
	(descriptive_representation_item.description = `top') 
	(descriptive_representation_item.description = `bottom') 
	(descriptive_representation_item.description = `symmetrical')}]
 */		 
 // FD <- PD <- PDR -> R -> DRI
/* Remoeved 	
 public static void setFootprint_location(SdaiContext context, EGeneric_footprint_definition_armx armEntity)throws SdaiException {
	 //unset old values
	 unsetFootprint_location(context, armEntity);
	 
	 EPadstack_location_in_footprint_definition eplifd = armEntity.getLocationPadstack(null, null).getByIndex(1);
		LangUtils.Attribute_and_value_structure[] structure = {
		   new LangUtils.Attribute_and_value_structure(
		   jsdai.SQualified_measure_schema.CDescriptive_representation_item.attributeName(null), 
		   "text"),
		   new LangUtils.Attribute_and_value_structure(
		   jsdai.SQualified_measure_schema.CDescriptive_representation_item.attributeDescription(null), 
		   EPadstack_arrangement.toString(eplifd.getPadstack(null).getPadstack_location(null)).toLowerCase())
		   };
		   jsdai.SQualified_measure_schema.EDescriptive_representation_item edri = 
			   (jsdai.SQualified_measure_schema.EDescriptive_representation_item)
		   LangUtils.createInstanceIfNeeded(context, jsdai.SQualified_measure_schema.CDescriptive_representation_item.definition, structure);
		
		ERepresentation representation = CxAP210ARMUtilities.findRepresentation(context, armEntity,
							  "product definition context description",
							  "product definition context description");

		if (!representation.testItems(null)) {
		   representation.createItems(null);
		}
		representation.getItems(null).addUnordered(edri);
 	}


		public static void unsetFootprint_location(SdaiContext context, EGeneric_footprint_definition_armx armEntity) throws
		   SdaiException {
			CxAP210ARMUtilities.clearRepresentationItems(context, armEntity,
														 "product definition context description", "product definition context description",
														 "text");
		}
 	
*/    
//********** "template_definition" attributes
 	
 	/**
 	* Sets/creates data for setImplemented_function attribute.
 	*
 	* @param context SdaiContext.
 	* @param armEntity arm entity.
 	* @throws SdaiException
 	*/
 	public static void setImplemented_function(SdaiContext context, EStructured_printed_part_template_armx armEntity) throws SdaiException
 	{
 		CxPrinted_part_template_armx.setImplemented_function(context, armEntity);
 	}


 	/**
 	* Unsets/deletes data for setImplemented_function attribute.
 	*
 	* @param context SdaiContext.
 	* @param armEntity arm entity.
 	* @throws SdaiException
 	*/
 	public static void unsetImplemented_function(SdaiContext context, EStructured_printed_part_template_armx armEntity) throws SdaiException
   {
 		CxPrinted_part_template_armx.unsetImplemented_function(context, armEntity); 		
 	}


 	/**
 	* Sets/creates data for location attribute.
 	*
	*
 	* @param context SdaiContext.
 	* @param armEntity arm entity.
 	* @throws SdaiException
 	*/

 	public static void setStack(SdaiContext context, EStratum_stack_dependent_template_armx armEntity) throws SdaiException
 	{
 		CxStratum_stack_dependent_template_armx.setStack(context, armEntity);
 	}


 	/**
 	* Unsets/deletes data for physical_characteristic attribute.
 	*
 	* @param context SdaiContext.
 	* @param armEntity arm entity.
 	* @throws SdaiException
 	*/
 	public static void unsetStack(SdaiContext context, EStratum_stack_dependent_template_armx armEntity) throws SdaiException
   {
 		CxStratum_stack_dependent_template_armx.unsetStack(context, armEntity);
 	}
 	
 	
}
