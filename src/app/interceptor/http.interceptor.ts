import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpResponse, HttpXsrfTokenExtractor, HttpErrorResponse } from '@angular/common/http';
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
      tap(({
          next: (event) => {

              if (event instanceof HttpResponse && (event.status === 200 || event.status === 201)) {
                  console.log('event :>> ', event);
                  const newToken = event.headers.get('Authorization');
                  if (newToken) {
                      localStorage.setItem('token', newToken);
                  }
                  if (request.url.includes("authentication")) {
                      const newToken = event.body?.entries?.token
                      localStorage.setItem('token', `Bearer ${newToken}`);

                  }
              }
          },
          error: (err) => {
              if (err instanceof HttpErrorResponse && (err.status === 404 || err.status === 401 || err.status >= 500)) {
                  localStorage.clear()
                  // this.router.navigate(['/auth/login']);
              }
          },
      }))
  );

  }
}
