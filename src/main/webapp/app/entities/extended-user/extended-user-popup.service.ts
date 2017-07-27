import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ExtendedUser } from './extended-user.model';
import { ExtendedUserService } from './extended-user.service';

@Injectable()
export class ExtendedUserPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private extendedUserService: ExtendedUserService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.extendedUserService.find(id).subscribe((extendedUser) => {
                this.extendedUserModalRef(component, extendedUser);
            });
        } else {
            return this.extendedUserModalRef(component, new ExtendedUser());
        }
    }

    extendedUserModalRef(component: Component, extendedUser: ExtendedUser): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.extendedUser = extendedUser;
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
