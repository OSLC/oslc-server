/**
 * <copyright>
 * </copyright>
 */
package misa.xform.front.transforms;


import misa.xform.front.l10n.FrontMessages;
import misa.xform.model.Extract.ClassDef;
import misa.xform.model.Extract.Description;
import misa.xform.model.Extract.ExtractFactory;
import misa.xform.model.Extract.ExtractPackage;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.query.conditions.Condition;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;

import com.ibm.xtools.transform.authoring.CreateRule;
import com.ibm.xtools.transform.authoring.CustomRule;
import com.ibm.xtools.transform.authoring.DirectFeatureAdapter;
import com.ibm.xtools.transform.authoring.FeatureAdapter;
import com.ibm.xtools.transform.authoring.InstanceOfCondition;
import com.ibm.xtools.transform.authoring.MapTransform;
import com.ibm.xtools.transform.authoring.MoveRule;
import com.ibm.xtools.transform.authoring.Registry;
import com.ibm.xtools.transform.authoring.RuleExtension;
import com.ibm.xtools.transform.authoring.SubmapExtractor;
import com.ibm.xtools.transform.core.AbstractContentExtractor;
import com.ibm.xtools.transform.core.AbstractRule;


/**
 * <!-- begin-user-doc -->
 * An implementation of the 'ClassToClassDefTransform' from the mapping.
 * <!-- end-user-doc -->
 * @generated
 */

public class ClassToClassDefTransform extends MapTransform {
    /**
     * The transform id
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String CLASSTOCLASSDEF_TRANSFORM = "ClassToClassDef_Transform";//$NON-NLS-1$

   /**
     * The create rule id
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String CLASSTOCLASSDEF_CREATE_RULE = CLASSTOCLASSDEF_TRANSFORM + "_Create_Rule";//$NON-NLS-1$

   /**
     * The 'CLASSTOCLASSDEF_Name To Name' rule id
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String CLASSTOCLASSDEF_NAME_TO_NAME_RULE = CLASSTOCLASSDEF_TRANSFORM + "_NameToName_Rule";//$NON-NLS-1$
	
   /**
     * The 'CLASSTOCLASSDEF_OwnedAttribute To AttributeDef Using AttributeToAttributeDef' extractor id
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String CLASSTOCLASSDEF_OWNED_ATTRIBUTE_TO_ATTRIBUTE_DEF_USING_ATTRIBUTETOATTRIBUTEDEF_EXTRACTOR = CLASSTOCLASSDEF_TRANSFORM + "_OwnedAttributeToAttributeDef_UsingAttributeToAttributeDef_Extractor";//$NON-NLS-1$
	
   /**
     * The 'CLASSTOCLASSDEF_Class To Package' rule id
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String CLASSTOCLASSDEF_CLASS_TO_PACKAGE_RULE = CLASSTOCLASSDEF_TRANSFORM + "_ClassToPackage_Rule";//$NON-NLS-1$

  /**
     * The 'CLASSTOCLASSDEF_Class To Description' rule id
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String CLASSTOCLASSDEF_CLASS_TO_DESCRIPTION_RULE = CLASSTOCLASSDEF_TRANSFORM + "_ClassToDescription_Rule";//$NON-NLS-1$

  /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ClassToClassDefTransform(Registry registry, FeatureAdapter referenceAdapter) {
        this(CLASSTOCLASSDEF_TRANSFORM, FrontMessages.ClassToClassDef_Transform, registry, referenceAdapter);
    }

   /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ClassToClassDefTransform(String id, String name, Registry registry, FeatureAdapter referenceAdapter) {
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
        add(getOwnedAttributeToAttributeDef_UsingAttributeToAttributeDef_Extractor(registry));				
        add(getClassToPackage_Rule());				
        add(getClassToDescription_Rule());				
    }

   /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected Condition getAccept_Condition() {
        return new InstanceOfCondition(UMLPackage.Literals.CLASS);
    }

   /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected CreateRule getCreate_Rule(FeatureAdapter referenceAdapter) {
        CreateRule rule = new CreateRule(
            CLASSTOCLASSDEF_CREATE_RULE, 
            FrontMessages.ClassToClassDef_Transform_Create_Rule, 
            this,
            referenceAdapter,
            ExtractPackage.Literals.CLASS_DEF); 
            
        return rule;    
    }

   /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AbstractRule getNameToName_Rule() {
        MoveRule rule = new MoveRule(
            CLASSTOCLASSDEF_NAME_TO_NAME_RULE, 
            FrontMessages.ClassToClassDef_Transform_NameToName_Rule,
            new DirectFeatureAdapter(UMLPackage.Literals.NAMED_ELEMENT__NAME), 
            new DirectFeatureAdapter(ExtractPackage.Literals.CLASS_DEF__NAME));
      
        return rule;            
    }
					
   /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AbstractContentExtractor getOwnedAttributeToAttributeDef_UsingAttributeToAttributeDef_Extractor(Registry registry) {
        SubmapExtractor extractor = new SubmapExtractor(
            CLASSTOCLASSDEF_OWNED_ATTRIBUTE_TO_ATTRIBUTE_DEF_USING_ATTRIBUTETOATTRIBUTEDEF_EXTRACTOR, 
            FrontMessages.ClassToClassDef_Transform_OwnedAttributeToAttributeDef_UsingAttributeToAttributeDef_Extractor, 
            registry.get(AttributeToAttributeDefTransform.class, new DirectFeatureAdapter(ExtractPackage.Literals.CLASS_DEF__ATTRIBUTE_DEF)),
            new DirectFeatureAdapter(UMLPackage.Literals.STRUCTURED_CLASSIFIER__OWNED_ATTRIBUTE)); 
      
        extractor.setFilterCondition(new Condition() {
            public boolean isSatisfied(Object object) {
                return filterOwnedAttributeToAttributeDef_UsingAttributeToAttributeDef_Extractor((Property) object);
            }
        });

        return extractor;
    }

  /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
     protected boolean filterOwnedAttributeToAttributeDef_UsingAttributeToAttributeDef_Extractor(Property ownedAttribute_src) {
    	 return (ownedAttribute_src.getAssociation() == null);
     }

  /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AbstractRule getClassToPackage_Rule() {
        CustomRule rule = new CustomRule(
            CLASSTOCLASSDEF_CLASS_TO_PACKAGE_RULE, 
            FrontMessages.ClassToClassDef_Transform_ClassToPackage_Rule,
            new RuleExtension() {
                public void execute(EObject source, EObject target) {
                    executeClassToPackage_Rule((org.eclipse.uml2.uml.Class) source, (ClassDef) target);
                }
            });

        return rule;
    }

  /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @not generated
     */
     protected void executeClassToPackage_Rule(org.eclipse.uml2.uml.Class Class_src, ClassDef ClassDef_tgt) {
        String packageName = "";
        String delim = "";
        for (EObject container=Class_src.eContainer(); 
        	(container instanceof Package && (! (container instanceof Model))); 
        	container=container.eContainer()) {
        	packageName = ((Package) container).getName() + delim + packageName;
        	delim = ".";
        }
        ClassDef_tgt.setPackage(packageName);
     }

  /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AbstractRule getClassToDescription_Rule() {
        CustomRule rule = new CustomRule(
            CLASSTOCLASSDEF_CLASS_TO_DESCRIPTION_RULE, 
            FrontMessages.ClassToClassDef_Transform_ClassToDescription_Rule,
            new RuleExtension() {
                public void execute(EObject source, EObject target) {
                    executeClassToDescription_Rule((org.eclipse.uml2.uml.Class) source, (ClassDef) target);
                }
            });

        return rule;
    }

  /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @not generated
     */
     protected void executeClassToDescription_Rule(org.eclipse.uml2.uml.Class Class_src, ClassDef ClassDef_tgt) {
        if (Class_src.getOwnedComments().size() > 0) {
        	// there is at least one comment field - use the first one
        	Description description = ExtractFactory.eINSTANCE.createDescription();
        	ClassDef_tgt.setDescription(description);
        	description.setValue(Class_src.getOwnedComments().get(0).getBody());
        }
     }
					
}
