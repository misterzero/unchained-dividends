import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils, EventManager } from 'ng-jhipster';
import { UnchainedTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ExtendedUserDetailComponent } from '../../../../../../main/webapp/app/entities/extended-user/extended-user-detail.component';
import { ExtendedUserService } from '../../../../../../main/webapp/app/entities/extended-user/extended-user.service';
import { ExtendedUser } from '../../../../../../main/webapp/app/entities/extended-user/extended-user.model';

describe('Component Tests', () => {

    describe('ExtendedUser Management Detail Component', () => {
        let comp: ExtendedUserDetailComponent;
        let fixture: ComponentFixture<ExtendedUserDetailComponent>;
        let service: ExtendedUserService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [UnchainedTestModule],
                declarations: [ExtendedUserDetailComponent],
                providers: [
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ExtendedUserService,
                    EventManager
                ]
            }).overrideTemplate(ExtendedUserDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ExtendedUserDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ExtendedUserService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new ExtendedUser(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.extendedUser).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
