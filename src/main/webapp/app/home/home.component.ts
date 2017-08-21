import { Component, OnInit } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager } from 'ng-jhipster';
import { HomeService } from './home.service';
import { InvestorService } from '../entities/investor/investor.service';
import { Account, LoginModalService, Principal } from '../shared';
import { Investor } from '../entities/investor/investor.model';
import {Dividends} from '../entities/dividends.model';

@Component({
    selector: 'jhi-home',
    templateUrl: './home.component.html',
    styleUrls: [
        'home.css'
    ]

})
export class HomeComponent implements OnInit {
    account: Account;
    modalRef: NgbModalRef;
    investor: Investor;

    public tokenChartLabels = ['Other Tokens', 'Your Tokens'];
    public tokenChartData = [1, 1];
    public tokenChartType = 'doughnut';
    public dividendLabel = [];
    public dividendChartType = 'line';
    public dividendChartOptions: any = {
      responsive: true
    };
    public dividendChartData: Array<any> = [
      { data: [], label: '' }
    ];

    constructor(private principal: Principal,
                private loginModalService: LoginModalService,
                private eventManager: EventManager,
                private homeService: HomeService,
                private investorService: InvestorService
    ) {
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.account = account;
        });
        this.registerAuthenticationSuccess();
    }

    loadAll() {
        this.homeService.findMasterMoneyInvested().subscribe((tokens) => {
            this.tokenChartData = [tokens.value, 0];
            this.investorService.find(0).subscribe((investor) => {
                this.tokenChartData = [(tokens.value - investor.tokens), investor.tokens];
            });
        });

        this.homeService.findTotalDividends().subscribe((dividends) => {
            this.dividendChartData = [{data: Array.prototype.map.call(dividends, (a) => a.amount)
              .filter(function(dividen){
                return dividen;
            }), label: 'Total Dividends'}];
            this.dividendLabel = Array.from(new Array(this.dividendChartData[0].data.length), (val, index) => index + 1);
        });
    }

    public tokenChartClicked(e: any): void {
        console.log(e);
    }

    public tokenChartHovered(e: any): void {
        console.log(e);
    }

    public chartClicked(e: any): void {
      console.log(e);
    }

    public chartHovered(e: any): void {
      console.log(e);
    }

    registerAuthenticationSuccess() {
        this.eventManager.subscribe('authenticationSuccess', (message) => {
            this.principal.identity().then((account) => {
                this.account = account;
                this.loadAll();
            });
        });
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    login() {
        this.modalRef = this.loginModalService.open();
    }
}
