/**
 * 
 */
package runtime;

import org.testng.Reporter;

import com.changan.wx.utils.AccessToken;
import com.changan.wx.utils.WeixinUtil;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * @author Carter
 *
 */
public class Main {

	/**
	 * 
	 */
	public Main() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	  //获取access_token的接口地址（GET） 限200（次/天）  
	  public final static String access_token_url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=APPID&corpsecret=APPSECRET";
	  public final static String department_user_url = "https://qyapi.weixin.qq.com/cgi-bin/user/list?access_token=ACCESSTOKEN&department_id=DEPARTMENTID";
	  public final static String department_url = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=ACCESSTOKEN&id=ID";
	  
	  /** 
	    * 获取access_token 
	    *  
	    * @param appid 凭证 
	    * @param appsecret 密钥 
	    * @return 
	    */  
	   public static AccessToken getAccessToken(String appid, String appsecret) {
	       AccessToken accessToken = null;
	     
	       String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);  
	       JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", null);
	       // 如果请求成功  
	       if (null != jsonObject) {
	           try {  
	               accessToken = new AccessToken();  
	               accessToken.setToken(jsonObject.getString("access_token"));  
	               accessToken.setExpiresIn(jsonObject.getInt("expires_in"));  
	           } catch (JSONException e) {
	               accessToken = null;
					// 获取token失败  
	               Reporter.log("获取token失败 errcode:{"+jsonObject.getInt("errcode")+"} errmsg:{"+jsonObject.getString("errmsg")+"}");
	           }  
	       }  
	       return accessToken;
	   }
}
