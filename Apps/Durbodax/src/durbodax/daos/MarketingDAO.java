package durbodax.daos;

import durbodax.dtos.*;
import java.util.*;

/**
 * MarketingDAO - Interface to be overidden by DB specific DAO
 * @author gbeckenbaugh
 */
public interface MarketingDAO {

    public ArrayList<MarketingResponseDTO> selectMarketingStatsTopBottom (MarketingRequestDTO mReqIn);
    public ArrayList<MarketingResponseDTO> selectMarketingStatsByGroup (MarketingRequestDTO mReqIn);
}
