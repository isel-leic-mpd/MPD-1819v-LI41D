package pt.isel.leic.mpd.v1819.li41d.weatherapi.jsonDto;

public class SearchDto {
    private final SearchApiDto search_api;

    public SearchDto(SearchApiDto search_api) {
        this.search_api = search_api;
    }

    public SearchApiDto getSearch_api() {
        return search_api;
    }
}
