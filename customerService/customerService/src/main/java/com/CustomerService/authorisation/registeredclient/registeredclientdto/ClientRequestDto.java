package com.CustomerService.authorisation.registeredclient.registeredclientdto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRequestDto {// oicd client request dto
    private String clientId;// client id
    private String clientSecret;// client password (encoded)
    private String clientName;// client name
    private String redirectUris;// redirect url
    private String postLogoutRedirectUris;// post logout redirect url

}
//{
//        "clientId":"sahib",
//        "clientSecret":"sahib123",
//        "clientName":"sahib",
//        "redirectUris":"https://oauth.pstmn.io/v1/callback",
//        "postLogoutRedirectUris":"http://127.0.0.1:8090/"
//        }