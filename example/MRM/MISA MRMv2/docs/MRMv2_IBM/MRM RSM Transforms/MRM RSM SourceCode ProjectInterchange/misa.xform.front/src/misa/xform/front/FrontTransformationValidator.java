/**
 * <copyright>
 * </copyright>
 */
package misa.xform.front;


import com.ibm.xtools.transform.authoring.TransformationValidator; 

/**
 * A helper class that provides validation services to the transform and its provider 
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class FrontTransformationValidator extends TransformationValidator {
    
   /**
     * The singleton instance
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static FrontTransformationValidator INSTANCE = new FrontTransformationValidator();

   /**
     * The private constructor
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private FrontTransformationValidator() {
        //empty
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected String getPluginID() {
        return "misa.xform.front"; //$NON-NLS-1$
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.ibm.xtools.transform.authoring.TransformationValidator#isSourceElementValid(java.lang.Object)
     * @generated
     */
    protected boolean isSourceElementValid(Object source) {
    	//Remove @generated tag to add more source validation checks
    	return super.isSourceElementValid(source);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.ibm.xtools.transform.authoring.TransformationValidator#isTargetElementValid(java.lang.Object)
     * @generated
     */
    protected boolean isTargetElementValid(Object target) {
    	//Remove @generated tag to add more target validation checks
    	return super.isTargetElementValid(target);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.ibm.xtools.transform.authoring.TransformationValidator#isAuxiliarySourceURIValid(java.lang.String)
     * @generated
     */
    protected boolean isAuxiliarySourceURIValid(String auxiliarySourceURI) {
    	//Remove @generated tag to add more auxiliary source validation checks
        return super.isAuxiliarySourceURIValid(auxiliarySourceURI);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.ibm.xtools.transform.authoring.TransformationValidator#isAuxiliaryTargetURIValid(java.lang.String)
     * @generated
     */
    protected boolean isAuxiliaryTargetURIValid(String auxiliaryTargetURI) {
    	//Remove @generated tag to add more auxiliary target validation checks
        return super.isAuxiliaryTargetURIValid(auxiliaryTargetURI);
    }

}
