package misa.xform.front.rules;

import java.util.Map;

import com.ibm.xtools.transform.authoring.JETRule;
import com.ibm.xtools.transform.core.ITransformContext;

public class ConditionalJETRule extends JETRule {

	public ConditionalJETRule(String jetTransformId) {
		super(jetTransformId);
	}

	@Override
	protected Object createTarget(ITransformContext context) throws Exception {
		return super.createTarget(context);
	}

	@Override
	public Object execute(ITransformContext context) throws Exception {
		Object generateRrcFiles = context.getPropertyValue("GenerateRrcFiles");
		if ((generateRrcFiles instanceof Boolean) && ((Boolean) generateRrcFiles).booleanValue()) {
			return super.execute(context);
		}
		return null;
	}

	@Override
	protected Map getTransactionOptions() {
		return super.getTransactionOptions();
	}

}
