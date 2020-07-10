import { TestBed } from '@angular/core/testing';
import { SearchServiceService } from './search-service.service';
describe('SearchServiceService', () => {
    let service;
    beforeEach(() => {
        TestBed.configureTestingModule({});
        service = TestBed.inject(SearchServiceService);
    });
    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});
//# sourceMappingURL=search-service.service.spec.js.map