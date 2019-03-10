import { IOwner } from 'app/shared/model//owner.model';

export interface IVehicle {
    id?: string;
    vehicleNum?: string;
    vehicleType?: string;
    brand?: string;
    owner?: IOwner;
}

export class Vehicle implements IVehicle {
    constructor(
        public id?: string,
        public vehicleNum?: string,
        public vehicleType?: string,
        public brand?: string,
        public owner?: IOwner
    ) {}
}
