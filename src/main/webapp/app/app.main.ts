import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { ProdConfig } from './blocks/config/prod.config';
import { VehicleManagementAppModule } from './app.module';

ProdConfig();

if (module['hot']) {
    module['hot'].accept();
}

platformBrowserDynamic()
    .bootstrapModule(VehicleManagementAppModule, { preserveWhitespaces: true })
    .then(success => console.log(`Application started`))
    .catch(err => console.error(err));
