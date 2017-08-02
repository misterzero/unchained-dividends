import { Component, OnInit, AfterViewInit, Renderer, ElementRef } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiLanguageService } from 'ng-jhipster';

import { Register } from './register.service';
import { ExtendedUserService } from '../../entities/extended-user/extended-user.service';
import { InvestorService } from '../../entities/investor/investor.service';
import { LoginModalService } from '../../shared';
import { ExtendedUser } from '../../entities/extended-user/extended-user.model';
import { Investor } from '../../entities/investor/investor.model';

@Component({
    selector: 'jhi-register',
    templateUrl: './register.component.html'
})
export class RegisterComponent implements OnInit, AfterViewInit {

    extendedUser: ExtendedUser;
    investor: Investor;
    confirmPassword: string;
    doNotMatch: string;
    error: string;
    errorAddressExists: string;
    errorEmailExists: string;
    errorUserExists: string;
    registerAccount: any;
    success: boolean;
    modalRef: NgbModalRef;

    constructor(
        private languageService: JhiLanguageService,
        private loginModalService: LoginModalService,
        private extendedUserService: ExtendedUserService,
        private investorService: InvestorService,
        private registerService: Register,
        private elementRef: ElementRef,
        private renderer: Renderer
    ) {
    }

    ngOnInit() {
        this.success = false;
        this.registerAccount = {};
        this.extendedUser = {};
        this.investor = {};
    }

    ngAfterViewInit() {
        this.renderer.invokeElementMethod(this.elementRef.nativeElement.querySelector('#login'), 'focus', []);
    }

    register() {
        if (this.registerAccount.password !== this.confirmPassword) {
            this.doNotMatch = 'ERROR';
        } else {
            this.doNotMatch = null;
            this.error = null;
            this.errorUserExists = null;
            this.errorEmailExists = null;
            this.languageService.getCurrent().then((key) => {
                this.registerAccount.langKey = key;
                this.registerService.save(this.registerAccount).flatMap(function(user) {
                    this.extendedUser.accountId = user.id;
                    this.investor.accountId = user.id;
                    return this.extendedUserService.create(this.extendedUser);
                }).flatMap(function(x) {
                    return this.investorService.create(this.investor);
                }).subscribe(() => {
                    this.success = true;
                }, (response) => this.processError(response));
            });
        }
    }

    openLogin() {
        this.modalRef = this.loginModalService.open();
    }

    private processError(response) {
        this.success = null;
        if (response.status === 400 && response._body === 'login already in use') {
            this.errorUserExists = 'ERROR';
        } else if (response.status === 400 && response._body === 'email address already in use') {
            this.errorEmailExists = 'ERROR';
        } else {
            this.error = 'ERROR';
        }
    }
}
