package misa.xform.front.rules;

import misa.xform.model.Extract.Root;
import misa.xform.reqpro.ReqProUpdater;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.ibm.xtools.transform.authoring.TransactionalRule;
import com.ibm.xtools.transform.core.ITransformContext;

public class ConditionalReqProChainRule extends TransactionalRule {

	public ConditionalReqProChainRule() {
		super("ReqProRule", "Call ReqPro");
	}

	@Override
	protected Object createTarget(ITransformContext context) throws Exception {
		Object updateReqProSchema = context
				.getPropertyValue("UpdateReqProSchema");
		Object rpProjectPath = context.getPropertyValue("ReqProProjectPath");
		Object rpUserName = context.getPropertyValue("ReqProUserName");
		Object rpPassword = context.getPropertyValue("ReqProPassword");
		Object rpPropertiesFilePath = context.getPropertyValue("ReqProPropertiesFilePath");

		if ((updateReqProSchema instanceof Boolean)
				&& ((Boolean) updateReqProSchema).booleanValue()) {
			ResourceSet resourceSet = ((EditingDomain) context
					.getPropertyValue("TargetEditingDomain")).getResourceSet();
			Resource resource = (Resource) resourceSet.getResources().get(0);
			if (resource != null
					&& resource.getContents().get(0) instanceof Root) {
				if (rpProjectPath instanceof String && rpProjectPath != null
						&& rpUserName instanceof String && rpUserName != null
						&& rpPassword instanceof String && rpPassword != null
						&& rpPropertiesFilePath instanceof String && rpPropertiesFilePath!=null) {
					ReqProUpdater.updateRequirementTypes(
							(String) rpProjectPath, (String) rpUserName,
							(String) rpPassword, (Root) resource.getContents()
									.get(0), (String) rpPropertiesFilePath);
				}
			}
		}
		return null;
	}

}
