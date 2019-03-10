import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { VehicleManagementOwnerModule } from './owner/owner.module';
import { VehicleManagementVehicleModule } from './vehicle/vehicle.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        VehicleManagementOwnerModule,
        VehicleManagementVehicleModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VehicleManagementEntityModule {}
