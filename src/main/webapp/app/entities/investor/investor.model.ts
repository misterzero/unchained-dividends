export class Investor {
    constructor(
        public id?: number,
        public tokens?: number,
        public dividendsEarned?: number,
        public isInvestor?: boolean,
        public moneyInvested?: number,
        public totalMoneyInvested?: number,
        public accountId?: number,
    ) {
        this.isInvestor = false;
    }
}
