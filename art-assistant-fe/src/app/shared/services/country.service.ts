import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment';
import {CountryModel} from "../models/country.model";
import {CountryApiConstants} from "../../core/constants/country-api.constants";

@Injectable()
export class CountryService {

  constructor(private http: HttpClient) {
  }

  public getCountries(): Observable<CountryModel[]> {
    return this.http.get<CountryModel[]>(`${environment.apiBaseUrl}/${CountryApiConstants.API_COUNTRY}`);
  }

  public getCountryById(countryId: number): Observable<CountryModel> {
    return this.http.get<CountryModel>(`${environment.apiBaseUrl}/${CountryApiConstants.API_COUNTRY}/${countryId}`);
  }
}
