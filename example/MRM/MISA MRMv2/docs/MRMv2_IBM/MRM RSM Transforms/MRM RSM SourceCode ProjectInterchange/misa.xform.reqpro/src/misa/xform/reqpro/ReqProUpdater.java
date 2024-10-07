package misa.xform.reqpro;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import misa.xform.model.Extract.AttributeDef;
import misa.xform.model.Extract.ClassDef;
import misa.xform.model.Extract.Root;

import com.rational.reqpro.rpx.Application;
import com.rational.reqpro.rpx._Attr;
import com.rational.reqpro.rpx._Attrs;
import com.rational.reqpro.rpx._Project;
import com.rational.reqpro.rpx._ReqType;
import com.rational.reqpro.rpx._ReqTypes;
import com.rational.reqpro.rpx.enumAttrDataTypes;
import com.rational.reqpro.rpx.enumAttrLookups;
import com.rational.reqpro.rpx.enumOpenProjectOptions;
import com.rational.reqpro.rpx.enumRelatedProjectOptions;
import com.rational.reqpro.rpx.enumReqColors;
import com.rational.reqpro.rpx.enumReqStyles;
import com.rational.reqpro.rpx.enumReqTypesLookups;
import com.rational.reqpro.rpx.enumRequirementFlags;
import com.rational.rjcb.ComException;
import com.rational.rjcb.ComObjectProxy;

public class ReqProUpdater {

	public static void updateRequirementTypes(String reqproProjectPath,
			String reqproUserName, String reqproPassword, Root intermediateRoot, String rpPropFilePath)
			throws IOException {

		Properties reqProProperties = new Properties();	

		reqProProperties.load(new FileInputStream(rpPropFilePath));
		System.out.println(reqProProperties.keySet());
		System.out.println(reqProProperties.values());
		Application reqProApp = new Application();

		_Project reqProProject = reqProApp.OpenProject(reqproProjectPath,
				enumOpenProjectOptions.eOpenProjOpt_RQSFile, reqproUserName,
				reqproPassword, enumRequirementFlags.eReqFlag_Empty,
				enumRelatedProjectOptions.eRelatedProjOption_ConnectNone);

		_ReqTypes reqTypes = reqProProject.getReqTypes();
		log("ReqPro project " + reqproProjectPath);
		for (ClassDef classDef : intermediateRoot.getClassDef()) {
			try {
				_ReqType newRequirementType = null;

				try {
					newRequirementType = reqProProject.getReqType(classDef
							.getName(),
							enumReqTypesLookups.eReqTypesLookups_Name);
				} catch (ComException e) {
					log("Cannot find requiremnt type: " + classDef.getName()+ " error: " +e.getMessage());
				}

				String prefix = reqProProperties.getProperty(classDef.getName());
				if(prefix == null){
					log("Property error:  could not find code for " + classDef.getName());
					continue;
				}
				
				if (newRequirementType == null) {
					log("Create reqtype " + classDef.getName());
					newRequirementType = reqTypes.Add(classDef.getName(),
							prefix,
							enumReqStyles.eReqStyles_Normal,
							enumReqColors.eReqColors_Auto, 1, null, null, true);
				}
				_Attrs attributes = newRequirementType.getAttrs();

				for (AttributeDef attribute : classDef.getAttributeDef()) {
					_Attr attr = null;
					try {
						attr = attributes.getItem(attribute.getName(),
								enumAttrLookups.eAttrLookups_Label);
					} catch (ComException e) {
						log("Attr error : " + e.getMessage());
					}
					if (attr != null)
						continue;
					log("Create attr " + attribute.getName());
					log("DATA TYPE FOR "+ attribute.getName() +" = " + attribute.getTypeName());
					int reqProDataType = enumAttrDataTypes.eAttrDataTypes_Text;
					if(attribute.getTypeName()!=null && attribute.getTypeName().length()>0){
						if(attribute.getTypeName().contains("Date"))
							reqProDataType = enumAttrDataTypes.eAttrDataTypes_Date;
						else if(attribute.getTypeName().contains("SingleSelectList"))
							reqProDataType = enumAttrDataTypes.eAttrDataTypes_List;
						else if(attribute.getTypeName().contains("MultiSelectList"))
							reqProDataType = enumAttrDataTypes.eAttrDataTypes_MultiSelect;
						else if(attribute.getTypeName().contains("Real"))
							reqProDataType = enumAttrDataTypes.eAttrDataTypes_Real;
						else if(attribute.getTypeName().contains("Text"))
							reqProDataType = enumAttrDataTypes.eAttrDataTypes_Text;
						else if(attribute.getTypeName().contains("Integer"))
							reqProDataType = enumAttrDataTypes.eAttrDataTypes_Integer;
					}
					_Attr att = attributes.Add(attribute.getName(), 1, true,reqProDataType, null, false);
					if(reqProDataType == enumAttrDataTypes.eAttrDataTypes_List || 
							reqProDataType == enumAttrDataTypes.eAttrDataTypes_MultiSelect){
						att.getListItems().Add(1, "value", false);
					}
					
					log(att.getDataTypeName());
					log(new Integer(att.getDataType()).toString());
					
					if (attribute.getDescription() != null) {
					log("\t\t\tDesc: "
							+ attribute.getDescription().getValue());
					 }
				}
			} catch (Exception e) {
				log("ReqPro schema builder error: "
						+ e.getMessage());
			}
		}

		try{
			log("save project");
			reqProProject.Save();
			log("save done");
		}catch(Exception e){
			log(e.getMessage());
		}
		if (reqProProject != null) {
			reqProProject.CloseProject();
		}
		if (reqProApp != null) {
			ComObjectProxy obj = (ComObjectProxy) reqProApp;
			obj.release();
		}
		
		reqProProject = null;
		reqProApp = null;
		log("release reqpro objects");
	}
	
	private static void log( String msg ) {
		System.out.println(msg);
	}
}
