import { Headers } from '@angular/http';

export class ResponseWrapper {
    constructor(
        public headers: Headers,
        public json: any,
        public status?: any     // TODO: code smell, necessary for investor.service.ts line 47 to stop throwing an error, may cause more issues
    ) { }
}
