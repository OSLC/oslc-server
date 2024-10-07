/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package misa.xform.model.Extract.impl;

import java.util.Collection;

import misa.xform.model.Extract.ClassDef;
import misa.xform.model.Extract.ExtractPackage;
import misa.xform.model.Extract.Root;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link misa.xform.model.Extract.impl.RootImpl#getClassDef <em>Class Def</em>}</li>
 *   <li>{@link misa.xform.model.Extract.impl.RootImpl#getTargetProject <em>Target Project</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RootImpl extends EObjectImpl implements Root {
	/**
	 * The cached value of the '{@link #getClassDef() <em>Class Def</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassDef()
	 * @generated
	 * @ordered
	 */
	protected EList<ClassDef> classDef;

	/**
	 * The default value of the '{@link #getTargetProject() <em>Target Project</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetProject()
	 * @generated
	 * @ordered
	 */
	protected static final String TARGET_PROJECT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTargetProject() <em>Target Project</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetProject()
	 * @generated
	 * @ordered
	 */
	protected String targetProject = TARGET_PROJECT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RootImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExtractPackage.Literals.ROOT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ClassDef> getClassDef() {
		if (classDef == null) {
			classDef = new EObjectContainmentEList<ClassDef>(ClassDef.class, this, ExtractPackage.ROOT__CLASS_DEF);
		}
		return classDef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTargetProject() {
		return targetProject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetProject(String newTargetProject) {
		String oldTargetProject = targetProject;
		targetProject = newTargetProject;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExtractPackage.ROOT__TARGET_PROJECT, oldTargetProject, targetProject));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ExtractPackage.ROOT__CLASS_DEF:
				return ((InternalEList<?>)getClassDef()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ExtractPackage.ROOT__CLASS_DEF:
				return getClassDef();
			case ExtractPackage.ROOT__TARGET_PROJECT:
				return getTargetProject();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ExtractPackage.ROOT__CLASS_DEF:
				getClassDef().clear();
				getClassDef().addAll((Collection<? extends ClassDef>)newValue);
				return;
			case ExtractPackage.ROOT__TARGET_PROJECT:
				setTargetProject((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ExtractPackage.ROOT__CLASS_DEF:
				getClassDef().clear();
				return;
			case ExtractPackage.ROOT__TARGET_PROJECT:
				setTargetProject(TARGET_PROJECT_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ExtractPackage.ROOT__CLASS_DEF:
				return classDef != null && !classDef.isEmpty();
			case ExtractPackage.ROOT__TARGET_PROJECT:
				return TARGET_PROJECT_EDEFAULT == null ? targetProject != null : !TARGET_PROJECT_EDEFAULT.equals(targetProject);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (targetProject: ");
		result.append(targetProject);
		result.append(')');
		return result.toString();
	}

} //RootImpl
