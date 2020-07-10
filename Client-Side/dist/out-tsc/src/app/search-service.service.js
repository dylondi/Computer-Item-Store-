import { __decorate } from "tslib";
import { Injectable } from '@angular/core';
let SearchServiceService = class SearchServiceService {
    constructor(http) {
        this.http = http;
    }
    getComponents() {
        return this.http.get("http://localhost:8080/list_items");
    }
    searchComponent(name) {
        return this.http.get("http://localhost:8080/search/" + name);
    }
};
SearchServiceService = __decorate([
    Injectable({
        providedIn: 'root'
    })
], SearchServiceService);
export { SearchServiceService };
//# sourceMappingURL=search-service.service.js.map