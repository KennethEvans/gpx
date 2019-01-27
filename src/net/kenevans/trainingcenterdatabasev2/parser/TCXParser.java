package net.kenevans.trainingcenterdatabasev2.parser;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import net.kenevans.trainingcenterdatabasev2.AbstractSourceT;
import net.kenevans.trainingcenterdatabasev2.ActivityLapT;
import net.kenevans.trainingcenterdatabasev2.ActivityListT;
import net.kenevans.trainingcenterdatabasev2.ActivityT;
import net.kenevans.trainingcenterdatabasev2.HeartRateInBeatsPerMinuteT;
import net.kenevans.trainingcenterdatabasev2.PlanT;
import net.kenevans.trainingcenterdatabasev2.PositionT;
import net.kenevans.trainingcenterdatabasev2.SensorStateT;
import net.kenevans.trainingcenterdatabasev2.TrackT;
import net.kenevans.trainingcenterdatabasev2.TrackpointT;
import net.kenevans.trainingcenterdatabasev2.TrainingCenterDatabaseT;
import net.kenevans.trainingcenterdatabasev2.TrainingT;
import net.kenevans.trainingcenterdatabasev2.TrainingTypeT;

/*
 * Created on Jan 22, 2019
 * By Kenneth Evans, Jr.
 */

public class TCXParser
{
    /** Hard-coded file name for testing with the main method. */
    // private static final String TEST_FILE =
    // "C:/Users/evans/Documents/GPSLink/Polar/Kenneth_Evans_2018-08-10_09-02-44.tcx";
//    private static final String TEST_FILE = "C:/Users/evans/Documents/GPSLink/Polar/Kenneth_Evans_2019-01-21_14-50-53.tcx";
    private static final String TEST_FILE = "C:/Users/evans/Documents/GPSLink/FitnessHistoryDetail.tcx";

    /** This is the package specified when XJC was run. */
    private static String TRAINING_CENTER_DATABASE_V2_PACKAGE = "net.kenevans.trainingcenterdatabasev2";

    /**
     * Save a TrainingCenterDatabaseT object into a file with the given name.
     * 
     * @param tcx
     * @param fileName
     * @throws JAXBException
     */
    public static void save(String creator, TrainingCenterDatabaseT tcx,
        String fileName) throws JAXBException {
        save(creator, tcx, new File(fileName));
    }

    /**
     * Save a TrainingCenterDatabaseT object into a File.
     * 
     * @param tcx
     * @param file
     * @throws JAXBException
     */
    public static void save(String creator, TrainingCenterDatabaseT tcx,
        File file) throws JAXBException {
        // // Set the creator
        // if(creator != null) {
        // tcx.setCreator(creator);
        // }
        // // Reset the version
        // tcx.setVersion("1.1");

        // Create a new JAXBElement<TrainingCenterDatabaseT> for the marshaller
        QName qName = new QName("http://www.topografix.com/GPX/1/1", "tcx");
        JAXBElement<TrainingCenterDatabaseT> root = new JAXBElement<TrainingCenterDatabaseT>(
            qName, TrainingCenterDatabaseT.class, tcx);
        // Create a context
        JAXBContext jc = JAXBContext
            .newInstance(TRAINING_CENTER_DATABASE_V2_PACKAGE);
        // Create a marshaller
        Marshaller marshaller = jc.createMarshaller();
        // Set it to be formatted, otherwise it is one long line
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        // Need to set the schema location to pass Xerces 3.1.1 SaxCount
        marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
            "http://www.topografix.com/GPX/1/1 http://www.topografix.com/GPX/1/1/tcx.xsd");
        // Marshal
        marshaller.marshal(root, file);
    }

    /**
     * Parses a TCX file with the given name.
     * 
     * @param fileName The file name to parse.
     * @return The TrainingCenterDatabaseT corresponding to the top level of the
     *         input file.
     * @throws JAXBException
     */
    public static TrainingCenterDatabaseT parse(String fileName)
        throws JAXBException {
        return parse(new File(fileName));
    }

    /**
     * Parses a TCX file.
     * 
     * @param file The File to parse.
     * @return The TrainingCenterDatabaseT corresponding to the top level of the
     *         input file.
     * @throws JAXBException
     */
    @SuppressWarnings("unchecked")
    public static TrainingCenterDatabaseT parse(File file)
        throws JAXBException {
        TrainingCenterDatabaseT tcx = null;
        JAXBContext jc = JAXBContext
            .newInstance(TRAINING_CENTER_DATABASE_V2_PACKAGE);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        try {
            JAXBElement<TrainingCenterDatabaseT> root = (JAXBElement<TrainingCenterDatabaseT>)unmarshaller
                .unmarshal(file);
            tcx = root.getValue();
        } catch(JAXBException ex) {
            throw ex;
        }
        return tcx;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        String fileName = TEST_FILE;
        System.out.println(fileName);
        TrainingCenterDatabaseT tcx = null;
        try {
            tcx = parse(fileName);
        } catch(JAXBException ex) {
            System.out
                .println("Error creating JAXBContext: " + ex.getMessage());
            ex.printStackTrace();
            return;
        }
        ActivityListT activityList;
        List<ActivityT> activities;
        List<ActivityLapT> laps;
        List<TrackT> tracks;
        List<TrackpointT> trackPoints;
        // ExtensionsT trackPointExt;
        Short cad, hr;
        Double ele, dist;
        double lat, lon;
        HeartRateInBeatsPerMinuteT hrBpm;
        PositionT position;
        SensorStateT sensorState;
        XMLGregorianCalendar time, id;
        AbstractSourceT creator;
        String creatorName, trainingPlanName, trainingPlanType;
        TrainingT training;
        PlanT plan;
        TrainingTypeT trainingType;

        activityList = tcx.getActivities();
        activities = activityList.getActivity();
        for(ActivityT activity : activities) {
            id = activity.getId();
            creator = activity.getCreator();
            creatorName = creator.getName();
            training = activity.getTraining();
            trainingType = null;
            trainingPlanName=null;
            trainingPlanType = null;
            if (training != null) {
                plan = training.getPlan();
                if (plan != null) {
                    trainingType = plan.getType();
                    trainingPlanName = plan.getName();
                    if(trainingType != null) {
                        trainingPlanType = trainingType.value();
                    }
                }
            }
            System.out.println("Activity : " + id + " creator=" + creatorName);
            System.out.println(
                "  Plan: " + trainingPlanName + " Type: " + trainingPlanType);
            laps = activity.getLap();
            for(ActivityLapT lap : laps) {
                tracks = lap.getTrack();
                int nTracks = 0;
                for(TrackT track : tracks) {
                    System.out.println("  Track " + nTracks++);
                    trackPoints = track.getTrackpoint();
                    for(TrackpointT trackPoint : trackPoints) {
                        ele = trackPoint.getAltitudeMeters();
                        cad = trackPoint.getCadence();
                        dist = trackPoint.getDistanceMeters();
                        hrBpm = trackPoint.getHeartRateBpm();
                        if(hrBpm != null) {
                            hr = hrBpm.getValue();
                        } else {
                            hr = null;
                        }
                        position = trackPoint.getPosition();
                        if(position == null) {
                            lat = Double.NaN;
                            lon = Double.NaN;
                        } else {
                            lat = position.getLatitudeDegrees();
                            lon = position.getLongitudeDegrees();
                        }
                        // trackPointExt = trackPoint.getExtensions();
                        sensorState = trackPoint.getSensorState();
                        time = trackPoint.getTime();
                        System.out.println("    Trackpoint " + time);
                        System.out.println("      lat=" + lat + " lon=" + lon
                            + " ele=" + ele + " hr=" + hr + " cad=" + cad
                            + " dist=" + dist + " sensorState=" + sensorState);
                    }
                }
            }
            System.out.println();
            System.out.println("All Done");
        }
    }

}