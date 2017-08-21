import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { ExtendedUser } from './extended-user.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class ExtendedUserService {

    private resourceUrl = 'api/extended-users';

    constructor(private http: Http) { }

    create(extendedUser: ExtendedUser): Observable<ExtendedUser> {
        const copy = this.convert(extendedUser);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(extendedUser: ExtendedUser): Observable<ExtendedUser> {
        const copy = this.convert(extendedUser);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<ExtendedUser> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            return res.json();
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convert(extendedUser: ExtendedUser): ExtendedUser {
        const copy: ExtendedUser = Object.assign({}, extendedUser);
        return copy;
    }
}
