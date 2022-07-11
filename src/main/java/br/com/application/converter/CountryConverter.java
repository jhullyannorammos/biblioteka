package br.com.application.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;



import br.com.application.model.Country;
import br.com.application.service.CountryService;

@Named
@FacesConverter(value = "countryConverter")
public class CountryConverter implements Converter {

    @Inject
    private CountryService countryService;

    public Country getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return countryService.getCountriesAsMap().get(Integer.parseInt(value));
            }
            catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid country."));
            }
        }
        else {
            return null;
        }
    }

    public String getAsString(FacesContext context, UIComponent component, Country value) {
        if (value != null) {
            return String.valueOf(value.getId());
        }
        else {
            return null;
        }
    }

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
            return String.valueOf(((UIComponent) value).getId());
        }
        else {
            return null;
        }
	}

}
