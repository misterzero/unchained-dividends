import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Dividend } from './dividend.model';
import { DividendService } from './dividend.service';

@Injectable()
export class DividendPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private dividendService: DividendService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.dividendService.find(id).subscribe((dividend) => {
                if (dividend.date) {
                    dividend.date = {
                        year: dividend.date.getFullYear(),
                        month: dividend.date.getMonth() + 1,
                        day: dividend.date.getDate()
                    };
                }
                this.dividendModalRef(component, dividend);
            });
        } else {
            return this.dividendModalRef(component, new Dividend());
        }
    }

    dividendModalRef(component: Component, dividend: Dividend): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.dividend = dividend;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        });
        return modalRef;
    }
}
