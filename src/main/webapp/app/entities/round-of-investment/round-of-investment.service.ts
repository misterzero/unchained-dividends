import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { DateUtils } from 'ng-jhipster';

import { RoundOfInvestment } from './round-of-investment.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class RoundOfInvestmentService {

    private resourceUrl = 'api/round-of-investments';

    constructor(private http: Http, private dateUtils: DateUtils) { }

    create(roundOfInvestment: RoundOfInvestment): Observable<RoundOfInvestment> {
        const copy = this.convert(roundOfInvestment);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(roundOfInvestment: RoundOfInvestment): Observable<RoundOfInvestment> {
        const copy = this.convert(roundOfInvestment);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<RoundOfInvestment> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
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
        for (let i = 0; i < jsonResponse.length; i++) {
            this.convertItemFromServer(jsonResponse[i]);
        }
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convertItemFromServer(entity: any) {
        entity.endDate = this.dateUtils
            .convertLocalDateFromServer(entity.endDate);
    }

    private convert(roundOfInvestment: RoundOfInvestment): RoundOfInvestment {
        const copy: RoundOfInvestment = Object.assign({}, roundOfInvestment);
        copy.endDate = this.dateUtils
            .convertLocalDateToServer(roundOfInvestment.endDate);
        return copy;
    }
}
