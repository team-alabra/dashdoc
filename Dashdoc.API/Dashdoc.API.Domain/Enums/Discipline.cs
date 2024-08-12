using System.ComponentModel;

namespace Dashdoc.API.Domain.Enums;

public enum Discipline
{
    [Description("Speech Therapy")]
    SpeechTherapy,
    [Description("Occupational Therapy")]
    OccupationalTherapy,
    [Description("Physical Therapy")]
    PhysicalTherapy,
    [Description("Counseling")]
    Counseling,
    [Description("Psych Ed.")]
    PsychEd,
    [Description("Hearing")]
    Hearing,
    [Description("Vision")]
    Vision,
    [Description("Assistive Tech")]
    AssistiveTech,
    [Description("Special Ed. Support")]
    SpecialEdSupport,
    [Description("Applied Behavioral Analysis")]
    ABA
}