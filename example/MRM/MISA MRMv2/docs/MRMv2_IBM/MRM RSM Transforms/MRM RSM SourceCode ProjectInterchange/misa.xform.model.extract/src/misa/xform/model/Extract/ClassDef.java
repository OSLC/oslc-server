/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package misa.xform.model.Extract;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class Def</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link misa.xform.model.Extract.ClassDef#getAttributeDef <em>Attribute Def</em>}</li>
 *   <li>{@link misa.xform.model.Extract.ClassDef#getDescription <em>Description</em>}</li>
 *   <li>{@link misa.xform.model.Extract.ClassDef#getName <em>Name</em>}</li>
 *   <li>{@link misa.xform.model.Extract.ClassDef#getPackage <em>Package</em>}</li>
 * </ul>
 * </p>
 *
 * @see misa.xform.model.Extract.ExtractPackage#getClassDef()
 * @model extendedMetaData="name='ClassDef' kind='elementOnly'"
 * @generated
 */
public interface ClassDef extends EObject {
	/**
	 * Returns the value of the '<em><b>Attribute Def</b></em>' containment reference list.
	 * The list contents are of type {@link misa.xform.model.Extract.AttributeDef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attribute Def</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attribute Def</em>' containment reference list.
	 * @see misa.xform.model.Extract.ExtractPackage#getClassDef_AttributeDef()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='attributeDef' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<AttributeDef> getAttributeDef();

	/**
	 * Returns the value of the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' containment reference.
	 * @see #setDescription(Description)
	 * @see misa.xform.model.Extract.ExtractPackage#getClassDef_Description()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='description' namespace='##targetNamespace'"
	 * @generated
	 */
	Description getDescription();

	/**
	 * Sets the value of the '{@link misa.xform.model.Extract.ClassDef#getDescription <em>Description</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' containment reference.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(Description value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see misa.xform.model.Extract.ExtractPackage#getClassDef_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='name' namespace='##targetNamespace'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link misa.xform.model.Extract.ClassDef#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Package</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Package</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Package</em>' attribute.
	 * @see #setPackage(String)
	 * @see misa.xform.model.Extract.ExtractPackage#getClassDef_Package()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='package' namespace='##targetNamespace'"
	 * @generated
	 */
	String getPackage();

	/**
	 * Sets the value of the '{@link misa.xform.model.Extract.ClassDef#getPackage <em>Package</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package</em>' attribute.
	 * @see #getPackage()
	 * @generated
	 */
	void setPackage(String value);

} // ClassDef
