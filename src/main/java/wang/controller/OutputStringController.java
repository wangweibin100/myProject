package main.java.wang.controller;

import org.springframework.stereotype.Controller;

/**
 * �ӿ����JSON��ʽ
 * restful
 * {"status":"0|1|2","info":message,"result":result}
 * status��0.ʧ�ܣ�1.�ɹ���2.��δ������
 * info�����ط�������Ϣ
 * result�����ز�ѯ���
 * @author ctk
 *
 */
@Controller
public class OutputStringController {
    //���سɹ���Ϣ
//    public String success(String info){
//        JSONObject result = new JSONObject();
//        result.put("status", 1);
//        if(info == null)
//            result.put("info", "");
//        else
//            result.put("info", info);
//        result.put("result", "");
//        return result.toString();
//    }
//    //����ʧ����Ϣ
//    public String failure(String info){
//        JSONObject result = new JSONObject();
//        result.put("status",0);
//        if(info == null)
//            result.put("info", "");
//        else
//            result.put("info", info);
//        result.put("result", "");
//        return result.toString();
//    }
//    //���ع�ǰ��ʹ�õ�result-�ɹ�
//    public String resultSuccess(String info,String resultStr){
//        JSONObject result = new JSONObject();
//        result.put("status",1);
//        if(info == null)
//            result.put("info", "");
//        else
//            result.put("info", info);
//        if(resultStr == null)
//            result.put("result", "");
//        else
//            result.put("result", resultStr);
//        return result.toString();
//    }
//    //���ع�ǰ��ʹ�õ�result-ʧ��
//    public String resultFailure(String info,String resultStr){
//        JSONObject result = new JSONObject();
//        result.put("status",0);
//        if(info == null)
//            result.put("info", "");
//        else
//            result.put("info", info);
//        if(resultStr == null)
//            result.put("result", "");
//        else
//            result.put("result", resultStr);
//        return result.toString();
//    }
}