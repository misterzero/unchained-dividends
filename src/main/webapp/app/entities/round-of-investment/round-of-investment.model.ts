import { Investor } from '../investor';
export class RoundOfInvestment {
    constructor(
        public id?: number,
        public endDate?: any,
        public totalMoneyInvested?: number,
        public tokenValue?: number,
        public investor?: Investor,
    ) {
    }
}
