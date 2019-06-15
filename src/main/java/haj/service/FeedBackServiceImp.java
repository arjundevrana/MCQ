package haj.service;

import haj.model.FeedBack;
import haj.model.User;
import haj.repository.FeedBackRepository;
import haj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FeedBackServiceImp implements FeedBackService {
    @Override
    public Optional<FeedBack> findById(long id) {
        Optional<FeedBack>  feedBack = feedBackRepository.findById(id);
        return  feedBack;
    }

    @Override
    public void deleteFeedBacById(long id) {

    }

    @Autowired
    FeedBackRepository feedBackRepository;

    public FeedBack createFeedBac(FeedBack feedBack) {
        // TODO Auto-generated method stub
        FeedBack saveFeedBack = feedBackRepository.save(feedBack);
        return saveFeedBack;
    }

    public List<FeedBack> getFeedBack() {
        // TODO Auto-generated method stub
        return (List<FeedBack>) feedBackRepository.findAll();
    }


    public FeedBack update(FeedBack feedBack, long l) {
        // TODO Auto-generated method stub
        return feedBackRepository.save(feedBack);
    }

    public FeedBack updatePartially(FeedBack feedBack, long id) {
        // TODO Auto-generated method stub
        Optional<FeedBack> userFeedBack = findById(id);
        return feedBackRepository.save(userFeedBack.get());
    }
}