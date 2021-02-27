package post.cloud.mobile;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.Map;

/**
 * 手机号-短信验证码
 */
public class PhoneSmsTokenGranter extends CustomAbstractTokenGranter {

    private static final String PHONE_SMS = "phone_sms";

    private CustomUserDetailService customUserDetailService;

    public PhoneSmsTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, CustomUserDetailService  customUserDetailService) {
        super(tokenServices, clientDetailsService, requestFactory, PHONE_SMS);
        this.customUserDetailService = customUserDetailService;
    }

    @Override
    protected UserDetails getUserDetails(Map<String, String> parameters) {
        String phone = parameters.get("phone");
        String smsCode = parameters.get("sms_code");
        return customUserDetailService.loadByPhone(phone,smsCode);
    }
}

