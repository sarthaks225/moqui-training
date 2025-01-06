// assignment 3 task 2 
// Groovy service for logic and data creation

import org.moqui.context.ExecutionContext
import org.moqui.entity.EntityValue

ExecutionContext ec = context.ec

// Input parameters from context
def trainingName = context.trainingName
def trainingDate = context.trainingDate
def trainingPrice = context.trainingPrice
def trainingDuration = context.trainingDuration

// validating required fields
if (!trainingName) {
    ec.message.addError("Training name is required.")
    return
}

if (!trainingDate) {
    ec.message.addError("Training date is required.")
    return
}

// Explicitly generate a unique ID
// first parameter is entityName second is tenant Id third parameter is sequence name
def trainingId = ec.entity.sequencedIdPrimary("MoquiTraining", null, null)

// Create the MoquiTraining entity record
EntityValue trainingRecord = ec.entity.makeValue("moqui.training.MoquiTraining")

trainingRecord.set("trainingId", trainingId) // Explicitly seting trainingId
trainingRecord.set("trainingName", trainingName)
trainingRecord.set("trainingDate", trainingDate)


// below two fields are not mandatory
if (trainingPrice != null) trainingRecord.set("trainingPrice", trainingPrice)
if (trainingDuration != null) trainingRecord.set("trainingDuration", trainingDuration)

// Save the record
trainingRecord = trainingRecord.create()

// Set the output parameter
context.trainingId = trainingRecord.get("trainingId")