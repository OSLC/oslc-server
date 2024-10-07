/**
 * <copyright>
 * </copyright>
 */
package misa.xform.front.transforms;


import com.ibm.xtools.transform.authoring.CreateRule;
import com.ibm.xtools.transform.authoring.CustomRule;
import com.ibm.xtools.transform.authoring.DirectFeatureAdapter;
import com.ibm.xtools.transform.authoring.FeatureAdapter;
import com.ibm.xtools.transform.authoring.InstanceOfCondition;
import com.ibm.xtools.transform.authoring.MapTransform;
import com.ibm.xtools.transform.authoring.MoveRule;
import com.ibm.xtools.transform.authoring.Registry;

import com.ibm.xtools.transform.authoring.RuleExtension;
import com.ibm.xtools.transform.core.AbstractRule;

import misa.xform.front.l10n.FrontMessages;

import misa.xform.model.Extract.AttributeDef;
import misa.xform.model.Extract.Description;
import misa.xform.model.Extract.ExtractFactory;
import misa.xform.model.Extract.ExtractPackage;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.query.conditions.Condition;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage; 


/**
 * <!-- begin-user-doc -->
 * An implementation of the 'AttributeToAttributeDefTransform' from the mapping.
 * <!-- end-user-doc -->
 * @generated
 */

public class AttributeToAttributeDefTransform extends MapTransform {
    /**
     * The transform id
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String ATTRIBUTETOATTRIBUTEDEF_TRANSFORM = "AttributeToAttributeDef_Transform";//$NON-NLS-1$

   /**
     * The create rule id
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String ATTRIBUTETOATTRIBUTEDEF_CREATE_RULE = ATTRIBUTETOATTRIBUTEDEF_TRANSFORM + "_Create_Rule";//$NON-NLS-1$

   /**
     * The 'ATTRIBUTETOATTRIBUTEDEF_Name To Name' rule id
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String ATTRIBUTETOATTRIBUTEDEF_NAME_TO_NAME_RULE = ATTRIBUTETOATTRIBUTEDEF_TRANSFORM + "_NameToName_Rule";//$NON-NLS-1$
	
   /**
     * The 'ATTRIBUTETOATTRIBUTEDEF_Property To TypeName' rule id
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String ATTRIBUTETOATTRIBUTEDEF_PROPERTY_TO_TYPE_NAME_RULE = ATTRIBUTETOATTRIBUTEDEF_TRANSFORM + "_PropertyToTypeName_Rule";//$NON-NLS-1$

  /**
     * The 'ATTRIBUTETOATTRIBUTEDEF_Property To Description' rule id
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String ATTRIBUTETOATTRIBUTEDEF_PROPERTY_TO_DESCRIPTION_RULE = ATTRIBUTETOATTRIBUTEDEF_TRANSFORM + "_PropertyToDescription_Rule";//$NON-NLS-1$

  /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AttributeToAttributeDefTransform(Registry registry, FeatureAdapter referenceAdapter) {
        this(ATTRIBUTETOATTRIBUTEDEF_TRANSFORM, FrontMessages.AttributeToAttributeDef_Transform, registry, referenceAdapter);
    }

   /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AttributeToAttributeDefTransform(String id, String name, Registry registry, FeatureAdapter referenceAdapter) {
        super(id, name, registry, referenceAdapter);
        addTransformElements(registry);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private void addTransformElements(Registry registry) {
    	// You may add more transform element before the generated ones here
    	// Remember to remove the @generated tag or add NOT to it
    	addGeneratedTransformElements(registry);
    	// You may add more transform element after the generated ones here
    	// Remember to remove the @generated tag or add NOT to it
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private void addGeneratedTransformElements(Registry registry) {
        add(getNameToName_Rule());				
        add(getPropertyToTypeName_Rule());				
        add(getPropertyToDescription_Rule());				
    }

   /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected Condition getAccept_Condition() {
        return new InstanceOfCondition(UMLPackage.Literals.PROPERTY);
    }

   /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected CreateRule getCreate_Rule(FeatureAdapter referenceAdapter) {
        CreateRule rule = new CreateRule(
            ATTRIBUTETOATTRIBUTEDEF_CREATE_RULE, 
            FrontMessages.AttributeToAttributeDef_Transform_Create_Rule, 
            this,
            referenceAdapter,
            ExtractPackage.Literals.ATTRIBUTE_DEF); 
            
        return rule;    
    }

   /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AbstractRule getNameToName_Rule() {
        MoveRule rule = new MoveRule(
            ATTRIBUTETOATTRIBUTEDEF_NAME_TO_NAME_RULE, 
            FrontMessages.AttributeToAttributeDef_Transform_NameToName_Rule,
            new DirectFeatureAdapter(UMLPackage.Literals.NAMED_ELEMENT__NAME), 
            new DirectFeatureAdapter(ExtractPackage.Literals.ATTRIBUTE_DEF__NAME));
      
        return rule;            
    }

  /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AbstractRule getPropertyToTypeName_Rule() {
        CustomRule rule = new CustomRule(
            ATTRIBUTETOATTRIBUTEDEF_PROPERTY_TO_TYPE_NAME_RULE, 
            FrontMessages.AttributeToAttributeDef_Transform_PropertyToTypeName_Rule,
            new RuleExtension() {
                public void execute(EObject source, EObject target) {
                    executePropertyToTypeName_Rule((Property) source, (AttributeDef) target);
                }
            });

        return rule;
    }

  /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @not generated
     */
     protected void executePropertyToTypeName_Rule(Property Property_src, AttributeDef AttributeDef_tgt) {
    	 String typeName = "";
    	 Type srcType = Property_src.getType();
    	 if (srcType instanceof PrimitiveType) {
    		 typeName = ((PrimitiveType) srcType).getQualifiedName();
    	 } else if (srcType instanceof org.eclipse.uml2.uml.Classifier) {
    		 typeName = ((Classifier) srcType).getQualifiedName();
    	 }
    	 AttributeDef_tgt.setTypeName(typeName);
     }

  /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AbstractRule getPropertyToDescription_Rule() {
        CustomRule rule = new CustomRule(
            ATTRIBUTETOATTRIBUTEDEF_PROPERTY_TO_DESCRIPTION_RULE, 
            FrontMessages.AttributeToAttributeDef_Transform_PropertyToDescription_Rule,
            new RuleExtension() {
                public void execute(EObject source, EObject target) {
                    executePropertyToDescription_Rule((Property) source, (AttributeDef) target);
                }
            });

        return rule;
    }

  /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @not generated
     */
     protected void executePropertyToDescription_Rule(Property Property_src, AttributeDef AttributeDef_tgt) {
         if (Property_src.getOwnedComments().size() > 0) {
         	// there is at least one comment field - use the first one
         	Description description = ExtractFactory.eINSTANCE.createDescription();
         	AttributeDef_tgt.setDescription(description);
         	description.setValue(Property_src.getOwnedComments().get(0).getBody());
         }
     }
					
}
