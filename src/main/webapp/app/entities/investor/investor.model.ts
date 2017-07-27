export class Investor {
    constructor(
        public id?: number,
        public tokens?: number,
        public dividendsEarned?: number,
        public isInvestor?: boolean,
    ) {
        this.isInvestor = false;
    }
}
