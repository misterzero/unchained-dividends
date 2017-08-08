import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Rx';

import {ResponseWrapper, createRequestOption} from '../shared';
import {Master} from '../entities/master.model';
import {Dividends} from '../entities/dividends.model';

@Injectable()
export class HomeService {

    private resourceUrl = 'api/tokens';
    private dividendUrl = 'api/dividends';

    constructor(private http: Http) {
    }

    findMasterMoneyInvested(): Observable<Master> {
        return this.http.get(this.resourceUrl).map((res: Response) => {
            return res.json();
        });
    }

    findTotalDividends(): Observable<Dividends> {
        return this.http.get(this.dividendUrl).map((res: Response) => {
            return res.json();
        });
    }
}
