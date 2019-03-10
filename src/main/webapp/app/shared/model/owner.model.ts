import { IVehicle } from 'app/shared/model//vehicle.model';

export interface IOwner {
    id?: string;
    firstName?: string;
    middleName?: string;
    lastName?: string;
    mobileNum?: number;
    alterNateMobileNo?: number;
    orgEmail?: string;
    personalEmailOne?: string;
    personalEmailTwo?: string;
    vehicles?: IVehicle[];
}

export class Owner implements IOwner {
    constructor(
        public id?: string,
        public firstName?: string,
        public middleName?: string,
        public lastName?: string,
        public mobileNum?: number,
        public alterNateMobileNo?: number,
        public orgEmail?: string,
        public personalEmailOne?: string,
        public personalEmailTwo?: string,
        public vehicles?: IVehicle[]
    ) {}
}
