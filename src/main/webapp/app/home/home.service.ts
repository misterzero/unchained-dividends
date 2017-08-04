import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { ResponseWrapper, createRequestOption } from '../shared';

@Injectable()
export class HomeService {

    private resourceUrl = 'api/dash';

    constructor(private http: Http) { }

    // create(investor: Investor): Observable<Investor> {
    //     const copy = this.convert(investor);
    //     return this.http.post(this.resourceUrl, copy).map((res: Response) => {
    //         return res.json();
    //     });
    // }

    // update(investor: Investor): Observable<Investor> {
    //     const copy = this.convert(investor);
    //     return this.http.put(this.resourceUrl, copy).map((res: Response) => {
    //         return res.json();
    //     });
    // }

    // find(id: number): Observable<Investor> {
    //     return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
    //         return res.json();
    //     });
    // }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    findMasterMoneyInvested(): Observable<String> {
      return this.http.get(this.resourceUrl).map((res: Response) => {
        return res.json();
      });
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    // private convert(investor: Investor): Investor {
    //     const copy: Investor = Object.assign({}, investor);
    //     return copy;
    // }
}
