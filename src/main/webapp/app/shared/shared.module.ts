import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { NgbDateAdapter } from '@ng-bootstrap/ng-bootstrap';

import { NgbDateMomentAdapter } from './util/datepicker-adapter';
import {
    VehicleManagementSharedLibsModule,
    VehicleManagementSharedCommonModule,
    JhiLoginModalComponent,
    HasAnyAuthorityDirective
} from './';

@NgModule({
    imports: [VehicleManagementSharedLibsModule, VehicleManagementSharedCommonModule],
    declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
    providers: [{ provide: NgbDateAdapter, useClass: NgbDateMomentAdapter }],
    entryComponents: [JhiLoginModalComponent],
    exports: [VehicleManagementSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VehicleManagementSharedModule {}
