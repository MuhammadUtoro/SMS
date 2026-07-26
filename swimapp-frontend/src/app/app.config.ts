import { ApplicationConfig, provideBrowserGlobalErrorListeners, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { provideKeycloak, withAutoRefreshToken, AutoRefreshTokenService, UserActivityService, createInterceptorCondition, IncludeBearerTokenCondition, includeBearerTokenInterceptor, INCLUDE_BEARER_TOKEN_INTERCEPTOR_CONFIG } from 'keycloak-angular';

import { routes } from './app.routes';

// Configure HttpClientInterceptors - to manage Bearer Token in the HTTP
// request header. Library (angular-js) doesn't automatically add the Bearer
// token to request, therefore we need to configure und use interceptors
const urlCondition = createInterceptorCondition<IncludeBearerTokenCondition>({
  urlPattern: /^(http:\/\/localhost:8080)(\/.*)?$/i,
  bearerPrefix: 'Bearer'
})

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideHttpClient(
      withInterceptors([
        includeBearerTokenInterceptor
      ])
    ),
    AutoRefreshTokenService,
    UserActivityService,
    provideKeycloak({
      config: {
        url: 'http://localhost:8081',
        realm: 'dio-project',
        clientId: 'swimapp'
      },
      initOptions: {
        // Configure keycloak to use silent-check-sso
        onLoad: 'check-sso',
        silentCheckSsoRedirectUri: window.location.origin + '/silent-check-sso.html',
        checkLoginIframe: false
      },
      features: [
        // Adding functionality of auto refresh token
        withAutoRefreshToken({
          sessionTimeout: 300000,
          onInactivityTimeout: 'logout'
        })
      ]
    }),
    {
      provide: INCLUDE_BEARER_TOKEN_INTERCEPTOR_CONFIG,
      useValue: [urlCondition]
    }
  ]
};
