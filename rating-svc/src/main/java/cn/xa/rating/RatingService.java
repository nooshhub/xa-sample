package cn.xa.rating;

import cn.xa.collaboration.CollaborationClient;
import cn.xa.collaboration.CollaborationDto;
import cn.xa.collaboration.ObjectClassId;
import cn.xa.common.exception.ServiceException;
import cn.xa.common.tcc.TccConfig;
import cn.xa.common.tcc.TccCoordinatorClient;
import cn.xa.task.TaskClient;
import cn.xa.task.TaskDto;
import cn.xa.tracking.TrackingClient;
import cn.xa.tracking.TrackingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @author Neal Shan
 * @since 0.0.1
 */
@Transactional
@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;
    private final TaskClient taskClient;
    private final TrackingClient trackingClient;
    private final CollaborationClient collaborationClient;
    private final RestTemplate restTemplate;
    private final TccCoordinatorClient tccCoordinatorClient;

    public RatingDto create(RatingDto ratingDto) {
        if (StringUtils.hasText(ratingDto.getReference())) {
            Rating rating = ratingRepository.findByReference(ratingDto.getReference());
            if (rating != null) {
                throw new ServiceException("rating reference is exist, please input a new one");
            }
        }

        Rating rating = ratingMapper.toEntity(ratingDto);
        ratingRepository.save(rating);

        return ratingMapper.toDto(rating);
    }

    public RatingDto findByTxId(String txId) {
        return ratingMapper.toDto(ratingRepository.findByTxId(txId));
    }

    public RatingDto delete(RatingDto ratingDto) {
        Rating rating = ratingMapper.toEntity(ratingDto);
        ratingRepository.save(rating);

//        String txId = ratingDto.getTxId();
//
//        String collaborationServiceUrl = String.format(TccConfig.COLLABORATION_TCC_URL, txId);
//        String trackingServiceUrl = String.format(TccConfig.TRACKING_TCC_URL, txId);
//        String taskServiceUrl = String.format(TccConfig.TASK_TCC_URL, txId);
//        restTemplate.delete(collaborationServiceUrl);
//        restTemplate.delete(trackingServiceUrl);
//        restTemplate.delete(taskServiceUrl);


        return ratingMapper.toDto(rating);
    }

    public RatingDto save(RatingDto ratingDto) {
        Rating rating = ratingMapper.toEntity(ratingDto);
        ratingRepository.save(rating);

//        String txId = ratingDto.getTxId();
//
//        String collaborationServiceUrl = String.format(TccConfig.COLLABORATION_TCC_URL, txId);
//        RequestEntity<Void> requestEntity = RequestEntity.put(URI.create(collaborationServiceUrl))
//                .contentType(new MediaType("application", "tcc"))
//                .build();
//        restTemplate.exchange(requestEntity, String.class);
//
//        String trackingServiceUrl = String.format(TccConfig.TRACKING_TCC_URL, txId);
//        requestEntity = RequestEntity.put(URI.create(trackingServiceUrl))
//                .contentType(new MediaType("application", "tcc"))
//                .build();
//        restTemplate.exchange(requestEntity, String.class);
//
//        String taskServiceUrl = String.format(TccConfig.TASK_TCC_URL, txId);
//        requestEntity = RequestEntity.put(URI.create(taskServiceUrl))
//                .contentType(new MediaType("application", "tcc"))
//                .build();
//        restTemplate.exchange(requestEntity, String.class);

        return ratingMapper.toDto(rating);

    }

}
