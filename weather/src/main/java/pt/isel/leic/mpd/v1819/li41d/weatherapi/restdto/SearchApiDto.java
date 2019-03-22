package pt.isel.leic.mpd.v1819.li41d.weatherapi.restdto;

public class SearchApiDto {
    private final SearchApiResultDto[] result;

    public SearchApiDto(SearchApiResultDto[] result) {
        this.result = result;
    }

    public SearchApiResultDto[] getResult() {
        return result;
    }
}
