import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { RoundOfInvestment } from './round-of-investment.model';
import { RoundOfInvestmentService } from './round-of-investment.service';

@Injectable()
export class RoundOfInvestmentPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private roundOfInvestmentService: RoundOfInvestmentService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.roundOfInvestmentService.find(id).subscribe((roundOfInvestment) => {
                if (roundOfInvestment.endDate) {
                    roundOfInvestment.endDate = {
                        year: roundOfInvestment.endDate.getFullYear(),
                        month: roundOfInvestment.endDate.getMonth() + 1,
                        day: roundOfInvestment.endDate.getDate()
                    };
                }
                this.roundOfInvestmentModalRef(component, roundOfInvestment);
            });
        } else {
            return this.roundOfInvestmentModalRef(component, new RoundOfInvestment());
        }
    }

    roundOfInvestmentModalRef(component: Component, roundOfInvestment: RoundOfInvestment): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.roundOfInvestment = roundOfInvestment;
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
