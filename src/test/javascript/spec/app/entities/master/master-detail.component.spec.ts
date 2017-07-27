import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils, EventManager } from 'ng-jhipster';
import { UnchainedTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { MasterDetailComponent } from '../../../../../../main/webapp/app/entities/master/master-detail.component';
import { MasterService } from '../../../../../../main/webapp/app/entities/master/master.service';
import { Master } from '../../../../../../main/webapp/app/entities/master/master.model';

describe('Component Tests', () => {

    describe('Master Management Detail Component', () => {
        let comp: MasterDetailComponent;
        let fixture: ComponentFixture<MasterDetailComponent>;
        let service: MasterService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [UnchainedTestModule],
                declarations: [MasterDetailComponent],
                providers: [
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    MasterService,
                    EventManager
                ]
            }).overrideTemplate(MasterDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MasterDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MasterService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Master(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.master).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
