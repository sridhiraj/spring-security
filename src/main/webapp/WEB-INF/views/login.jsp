<!-- index.html -->
<!DOCTYPE html>
<html>
<head>
    <!-- ... existing head content ... -->
    <script src="https://global.oktacdn.com/okta-signin-widget/7.13.1/js/okta-sign-in.min.js"></script>
</head>
<body>
      <script>
        var oktaConfig = {
            baseUrl: 'https://dev-57158829.okta.com/',
            issuer: 'https://dev-57158829.okta.com/oauth2/default',
            clientId: '0oaaeq14cbSdlQL7m5d7',
            redirectUri: 'http://localhost:8080/spring-security/authorization-code/callback',
            authParams: {
                responseType: ['token', 'id_token'],
                scopes: ['openid', 'email', 'profile']
            }
        };
		
        const oktaSignIn = new OktaSignIn(oktaConfig);
        
        oktaSignIn.authClient.token.getWithRedirect({
         	responseType: ['token', 'id_token'],
            scopes: ['openid', 'email', 'profile']
         });
            
    </script>
</body>
</html>
