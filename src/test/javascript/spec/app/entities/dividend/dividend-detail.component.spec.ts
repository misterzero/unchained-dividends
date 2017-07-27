import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils, EventManager } from 'ng-jhipster';
import { UnchainedTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { DividendDetailComponent } from '../../../../../../main/webapp/app/entities/dividend/dividend-detail.component';
import { DividendService } from '../../../../../../main/webapp/app/entities/dividend/dividend.service';
import { Dividend } from '../../../../../../main/webapp/app/entities/dividend/dividend.model';

describe('Component Tests', () => {

    describe('Dividend Management Detail Component', () => {
        let comp: DividendDetailComponent;
        let fixture: ComponentFixture<DividendDetailComponent>;
        let service: DividendService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [UnchainedTestModule],
                declarations: [DividendDetailComponent],
                providers: [
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    DividendService,
                    EventManager
                ]
            }).overrideTemplate(DividendDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DividendDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DividendService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Dividend(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.dividend).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
