/**
 * <copyright>
 * </copyright>
 */
package misa.xform.front.transforms;


import com.ibm.xtools.transform.authoring.CreateRule;
import com.ibm.xtools.transform.authoring.CustomExtractor;
import com.ibm.xtools.transform.authoring.CustomRule;
import com.ibm.xtools.transform.authoring.DirectFeatureAdapter;
import com.ibm.xtools.transform.authoring.ExtractorExtension;
import com.ibm.xtools.transform.authoring.FeatureAdapter;
import com.ibm.xtools.transform.authoring.InstanceOfCondition;
import com.ibm.xtools.transform.authoring.MapTransform;
import com.ibm.xtools.transform.authoring.Registry;
import com.ibm.xtools.transform.authoring.RuleExtension;

import com.ibm.xtools.transform.core.AbstractContentExtractor;
import com.ibm.xtools.transform.core.AbstractRule;

import java.util.Collection;
import misa.xform.front.l10n.FrontMessages;

import misa.xform.front.transforms.ClassToClassDefTransform;
import misa.xform.model.Extract.ExtractPackage;
import misa.xform.model.Extract.Root;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.query.conditions.Condition;

import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.UMLPackage; 


/**
 * <!-- begin-user-doc -->
 * An implementation of the 'ModelToRootTransform' from the mapping.
 * <!-- end-user-doc -->
 * @generated
 */

public class ModelToRootTransform extends MapTransform {
    /**
     * The transform id
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String MODELTOROOT_TRANSFORM = "ModelToRoot_Transform";//$NON-NLS-1$

   /**
     * The create rule id
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String MODELTOROOT_CREATE_RULE = MODELTOROOT_TRANSFORM + "_Create_Rule";//$NON-NLS-1$

   /**
     * The 'MODELTOROOT_PackagedElement To ClassDef Using ClassToClassDef' extractor id
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String MODELTOROOT_PACKAGED_ELEMENT_TO_CLASS_DEF_USING_CLASSTOCLASSDEF_EXTRACTOR = MODELTOROOT_TRANSFORM + "_PackagedElementToClassDef_UsingClassToClassDef_Extractor";//$NON-NLS-1$

  /**
     * The 'MODELTOROOT_Package To TargetProject' rule id
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String MODELTOROOT_PACKAGE_TO_TARGET_PROJECT_RULE = MODELTOROOT_TRANSFORM + "_PackageToTargetProject_Rule";//$NON-NLS-1$

  /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ModelToRootTransform(Registry registry, FeatureAdapter referenceAdapter) {
        this(MODELTOROOT_TRANSFORM, FrontMessages.ModelToRoot_Transform, registry, referenceAdapter);
    }

   /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ModelToRootTransform(String id, String name, Registry registry, FeatureAdapter referenceAdapter) {
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
        add(getPackagedElementToClassDef_UsingClassToClassDef_Extractor(registry));				
        add(getPackageToTargetProject_Rule());				
    }

   /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected Condition getAccept_Condition() {
        return new InstanceOfCondition(UMLPackage.Literals.PACKAGE);
    }

   /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected CreateRule getCreate_Rule(FeatureAdapter referenceAdapter) {
        CreateRule rule = new CreateRule(
            MODELTOROOT_CREATE_RULE, 
            FrontMessages.ModelToRoot_Transform_Create_Rule, 
            this,
            referenceAdapter,
            ExtractPackage.Literals.ROOT); 
            
        return rule;    
    }

   /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AbstractContentExtractor getPackagedElementToClassDef_UsingClassToClassDef_Extractor(Registry registry) {
        CustomExtractor extractor = new CustomExtractor(
            MODELTOROOT_PACKAGED_ELEMENT_TO_CLASS_DEF_USING_CLASSTOCLASSDEF_EXTRACTOR, 
            FrontMessages.ModelToRoot_Transform_PackagedElementToClassDef_UsingClassToClassDef_Extractor, 
            registry.get(ClassToClassDefTransform.class, new DirectFeatureAdapter(ExtractPackage.Literals.ROOT__CLASS_DEF)),
            new ExtractorExtension() {
                public Collection execute(EObject source) {
                    return extendPackagedElementToClassDef_UsingClassToClassDef_Extractor((org.eclipse.uml2.uml.Package) source);
                }
            });
      
        return extractor;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @not generated
     */
     protected Collection extendPackagedElementToClassDef_UsingClassToClassDef_Extractor(org.eclipse.uml2.uml.Package Package_src) {
        return getNestedClasses(Package_src);
     }
     
     /**
      * Returns all of the nested Classes under the package.  Note that this code
      * was never generated.
      */
     private EList<PackageableElement> getNestedClasses(Package pkg_src) {
    	 BasicEList<PackageableElement> retval = new BasicEList<PackageableElement>();
    	 EList<PackageableElement> packagedElements = pkg_src.getPackagedElements();
    	 if (packagedElements != null) {
    		 retval.addAll(packagedElements);
    	 }
    	 EList<Package> packages = pkg_src.getNestedPackages();
    	 for (Package pkg: packages) {
    		 EList<PackageableElement> nestedElements = getNestedClasses(pkg);
    		 if (nestedElements != null) {
    			 retval.addAll(nestedElements);
    		 }
    	 }
    	 return retval;
     }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AbstractRule getPackageToTargetProject_Rule() {
        CustomRule rule = new CustomRule(
            MODELTOROOT_PACKAGE_TO_TARGET_PROJECT_RULE, 
            FrontMessages.ModelToRoot_Transform_PackageToTargetProject_Rule,
            new RuleExtension() {
                public void execute(EObject source, EObject target) {
                    executePackageToTargetProject_Rule((org.eclipse.uml2.uml.Package) source, (Root) target);
                }
            });

        return rule;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
     protected void executePackageToTargetProject_Rule(org.eclipse.uml2.uml.Package Package_src, Root Root_tgt) {
        // TODO - this is currently hard-wired
        Root_tgt.setTargetProject("sample out project");
     }
				
}
