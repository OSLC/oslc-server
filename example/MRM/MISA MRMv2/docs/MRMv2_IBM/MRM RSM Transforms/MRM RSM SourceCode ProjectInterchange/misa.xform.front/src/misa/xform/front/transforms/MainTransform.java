/**
 * <copyright>
 * </copyright>
 */
package misa.xform.front.transforms;


import misa.xform.front.l10n.FrontMessages;
import misa.xform.front.transforms.AttributeToAttributeDefTransform;
import misa.xform.front.transforms.ClassToClassDefTransform;
import misa.xform.front.transforms.ModelToRootTransform; 


/**
 * A main transform that acts as a switch on the other transforms of the transformation
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class MainTransform extends com.ibm.xtools.transform.authoring.MainTransform {

   /**
     * The transformation id
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String TRANSFORM = "mainTransform";//$NON-NLS-1$

   /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MainTransform() {
        super(TRANSFORM, FrontMessages.mainTransform);
        add(ModelToRootTransform.class);
        add(ClassToClassDefTransform.class);
        add(AttributeToAttributeDefTransform.class);
    }

}
