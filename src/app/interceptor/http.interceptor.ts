import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpResponse, HttpXsrfTokenExtractor } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  // constructor(private authService: AuthService) {}

  constructor(private tokenExtractor: HttpXsrfTokenExtractor) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // Add authorization header
    const token = localStorage.getItem('token'); //this.authService.getAccessToken()
    // const lang = localStorage.getItem('lang')


    if (!request.url.includes('/api/admin/checkLogin')) {
      request = request.clone({
        setHeaders: {
          Authorization: `${token}`,
          // lang: lang
        },
      });
    }
    console.log('request :>> ', request);

    // const cookieheaderName = 'XSRF-TOKEN';
    // let csrfToken = this.tokenExtractor.getToken() as string;
    // if (csrfToken !== null && !request.headers.has(cookieheaderName)) {
    //   request = request.clone({ headers: request.headers.set(cookieheaderName, csrfToken) });
    // } 

    // console.log('request :>> ', request);
    // return next.handle(request);

     // Continue with the request and intercept the response
     return next.handle(request).pipe(
        tap((event) => {
            if (event instanceof HttpResponse && (event.status === 200 || event.status === 201)) {
                // console.log('event.headers :>> ', structuredClone(event.headers));
                const newToken = event.headers.get('Authorization');
                console.log('newToken :>> ', newToken);
                if (newToken) {
                  localStorage.setItem("token", newToken);
                }
              }
        })
      );

  }
}
