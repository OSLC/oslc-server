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
 * A representation of the model object '<em><b>Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link misa.xform.model.Extract.Root#getClassDef <em>Class Def</em>}</li>
 *   <li>{@link misa.xform.model.Extract.Root#getTargetProject <em>Target Project</em>}</li>
 * </ul>
 * </p>
 *
 * @see misa.xform.model.Extract.ExtractPackage#getRoot()
 * @model extendedMetaData="name='Root' kind='elementOnly'"
 * @generated
 */
public interface Root extends EObject {
	/**
	 * Returns the value of the '<em><b>Class Def</b></em>' containment reference list.
	 * The list contents are of type {@link misa.xform.model.Extract.ClassDef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class Def</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class Def</em>' containment reference list.
	 * @see misa.xform.model.Extract.ExtractPackage#getRoot_ClassDef()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='classDef' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<ClassDef> getClassDef();

	/**
	 * Returns the value of the '<em><b>Target Project</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Project</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Project</em>' attribute.
	 * @see #setTargetProject(String)
	 * @see misa.xform.model.Extract.ExtractPackage#getRoot_TargetProject()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='targetProject' namespace='##targetNamespace'"
	 * @generated
	 */
	String getTargetProject();

	/**
	 * Sets the value of the '{@link misa.xform.model.Extract.Root#getTargetProject <em>Target Project</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Project</em>' attribute.
	 * @see #getTargetProject()
	 * @generated
	 */
	void setTargetProject(String value);

} // Root
