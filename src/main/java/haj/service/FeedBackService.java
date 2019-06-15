package haj.service;

import haj.model.FeedBack;
import haj.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface FeedBackService {
    public FeedBack createFeedBac(FeedBack user);
    public List<FeedBack> getFeedBack();
    public Optional<FeedBack> findById(long id);
    public FeedBack update(FeedBack feedBack, long l);
    public void deleteFeedBacById(long id);
    public FeedBack updatePartially(FeedBack feedBack, long id);
}
